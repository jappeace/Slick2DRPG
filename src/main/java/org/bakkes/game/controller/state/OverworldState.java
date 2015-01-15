package org.bakkes.game.controller.state;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.bakkes.game.controller.events.key.IKeyListener;
import org.bakkes.game.controller.events.key.InventoryToggleListener;
import org.bakkes.game.model.entity.command.ICommand;
import org.bakkes.game.model.entity.npc.Person;
import org.bakkes.game.model.entity.npc.PersonTracker;
import org.bakkes.game.model.entity.player.Player;
import org.bakkes.game.model.map.Direction;
import org.bakkes.game.model.map.LayerdMap;
import org.bakkes.game.model.map.Tile;
import org.bakkes.game.view.IRenderable;
import org.bakkes.game.view.overworld.Camera;
import org.bakkes.game.view.overworld.EntityView;
import org.bakkes.game.view.overworld.InventoryGameComponent;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class OverworldState extends CommonGameState {
	public static final int PLAYING_STATE_ID = 0;

	private @Inject Player player;
	private @Inject Random random;
	private @Inject LayerdMap map;
	private @Inject PersonTracker tracker;


	private @Inject List<IKeyListener> keyListeners;
	private @Inject Provider<EntityView> entityViewProvider;
	private Tile clickedTile;
	private @Inject Camera camera;
	private List<IRenderable> translatedViews = new LinkedList<>();
	private static final int WILD_POKE_CHANCE = 2; // chance of encountering wild pokemone (1 in chance)

	@Override
	public int getID() {
		return PLAYING_STATE_ID;
	}

	@Override
	public void init(final GameContainer gc, final StateBasedGame arg1)
			throws SlickException {
		super.init(gc, arg1);
		map.load("outside");
		for(final Person person : tracker.getPeople()){
			final EntityView view = entityViewProvider.get();
			view.setEntity(person);
			translatedViews.add(view);
		}
        final EntityView view = entityViewProvider.get();
        view.setEntity(player);
		translatedViews.add(view);
		keyListeners.add(new InventoryToggleListener(this, new InventoryGameComponent(player)));
	}

	@Override
	public void update(final GameContainer gc, final StateBasedGame arg1, final int delta)
			throws SlickException {
		super.update(gc, arg1, delta);
		final Input input = gc.getInput();

		if(inputEnabled){
			handleMouseInput(input);
		}
		player.update(gc, delta);
		checkForWildPokeBattle(arg1);
	}
	private void checkForWildPokeBattle(final StateBasedGame game){
		if(!player.hasEnteredNewTile()){
			return;
		}
        if(!map.isGrass(player.getTile())) {
        	return;
        }

        if(random.nextInt(WILD_POKE_CHANCE) != 1) {
        	return;
        }
        ((BattleState)game.getState(BattleState.BATTLE_STATE_ID)).startWild();
        game.enterState(BattleState.BATTLE_STATE_ID, new FadeOutTransition(), new FadeInTransition());
	}
	private void handleMouseInput(final Input input){
        final Vector2f mousePos = new Vector2f(input.getMouseX(), input.getMouseY());
		if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
			onLeftMouseButton(mousePos);
		}
		if(input.isMousePressed(Input.MOUSE_RIGHT_BUTTON)){
			onRightMouseButton(mousePos);
		}
	}
	private void onRightMouseButton(final Vector2f mousePos){
		player.clearCommands();
	}
	private void onLeftMouseButton(Vector2f mousePos){
        mousePos = new Vector2f(mousePos.getX() + camera.cameraX, mousePos.getY() + camera.cameraY);
        clickedTile = Tile.createFromPixelsCoordinates(mousePos);

        if(!map.isBlocked(clickedTile)) {
            player.moveTo(clickedTile);
            return;
        }
        moveToNPC(); // if is an NPC
        clickedTile = null;
	}
	private void moveToNPC(){
        final Person person = tracker.findPersonByTile(clickedTile);
        if(person == null){
        	return;
        }
        final Tile facingTile = person.getDirectionTile();
        final CommonGameState state = this;
        Vector2f faceCorrection = new Vector2f(3,3);
        if(person.getFacing() == Direction.NORTH || person.getFacing() == Direction.WEST){
        	faceCorrection = new Vector2f(2,2); // nort and west need less correction, because they are drawn from top left, so the available tile is one less away
        }
        // first move 2 tiles away in the direction the npc is facing
        player.moveTo(new Tile(facingTile.multiply(faceCorrection)).plus(person.getTile()));

        // now move closer to garantee facing
        faceCorrection = faceCorrection.sub(new Vector2f(1,1));
        player.moveTo(new Tile(facingTile.multiply(faceCorrection)).plus(person.getTile()));

        // finaly press the spacebar
        player.addCommand(new ICommand(){
        	private boolean executed = false;
			@Override
			public void execute(final float tpf) {
				executed = true;
				state.keyPressed(Keyboard.KEY_SPACE, ' ');
			}

			@Override
			public boolean isDone() {
				return executed;
			}

			@Override
			public void onInterupt() {
				executed = true;
			}

        });
	}


	@Override
	public void render(final GameContainer gc, final StateBasedGame arg1, final Graphics g)
			throws SlickException {


		final Input input = gc.getInput();
		final Vector2f mousePos = new Vector2f(input.getMouseX(), input.getMouseY());

		camera.centerOn((int)player.getPosition().getX(), (int)player.getPosition().getY());
		camera.drawMap();

		camera.translateGraphics(g);
		if(clickedTile != null && !clickedTile.equals(player.getTile())) {
			g.setColor(new Color(0, 0, 255, 64));
			final Vector2f tl = clickedTile.topLeftPixels();
			g.fillRect(tl.x, tl.y, Tile.WIDTH, Tile.HEIGHT);
		}
		for(final IRenderable renderable : translatedViews){
			renderable.render(g);
		}
		camera.untranslateGraphics(g);

		final Vector2f mouseTile = Tile.PixelsToGridPixels(mousePos);
		g.setColor(Color.black);
		g.drawRect(mouseTile.getX(), mouseTile.getY(), Tile.WIDTH, Tile.HEIGHT);
		super.render(gc, arg1, g);
	}

	@Override
	public List<IKeyListener> getKeyListeners() {
		return keyListeners;
	}

}
