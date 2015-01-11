package org.bakkes.game.state;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.bakkes.game.Camera;
import org.bakkes.game.GameInfo;
import org.bakkes.game.World;
import org.bakkes.game.battle.Battle;
import org.bakkes.game.events.key.InventoryToggleListener;
import org.bakkes.game.events.key.MovementListener;
import org.bakkes.game.events.key.ScriptReloadListener;
import org.bakkes.game.events.key.TalkToNPCListener;
import org.bakkes.game.model.entity.Person;
import org.bakkes.game.model.entity.Player;
import org.bakkes.game.model.entity.command.ICommand;
import org.bakkes.game.model.map.LayerdMap;
import org.bakkes.game.model.map.Tile;
import org.bakkes.game.model.pokemon.Pokemon;
import org.bakkes.game.scripting.PokemonManager;
import org.bakkes.game.view.IRenderable;
import org.bakkes.game.view.InventoryGameComponent;
import org.bakkes.game.view.OverworldEntityView;
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
import org.newdawn.slick.util.Log;

public class OverworldState extends CommonGameState {
	public static final int PLAYING_STATE_ID = 0;

	private Player player;
	private Camera camera;
	private Tile clickedTile;
	private World world;
	private Random random = new Random();
	private List<IRenderable> translatedViews = new LinkedList<>();
	private static final int WILD_POKE_CHANCE = 5; // chance of encountering wild pokemone (1 in chance)

	public Player getPlayer() {
		return player;
	}

	@Override
	public int getID() {
		return PLAYING_STATE_ID;
	}

	@Override
	public void init(final GameContainer gc, final StateBasedGame arg1)
			throws SlickException {
		world = World.construct(this);
		super.init(gc, arg1);
		player = new Player(this);
		player.init(gc);
		GameInfo.getInstance().player = player;
		GameInfo.getInstance().stateGame = arg1;
		camera = new Camera(gc);

		translatedViews.add(new OverworldEntityView(player));
		addKeyListener(new TalkToNPCListener(this));
		addKeyListener(new ScriptReloadListener());
		addKeyListener(new MovementListener(this));
		addKeyListener(new InventoryToggleListener(this, new InventoryGameComponent(player)));
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
		checkForWildPokeBattle();
	}
	private void checkForWildPokeBattle(){
		if(!player.hasEnteredNewTile()){
			return;
		}
        if(!world.getLayerMap().isGrass(player.getTile())) {
        	return;
        }

        if(random.nextInt(WILD_POKE_CHANCE) != 1) {
        	return;
        }
        final BattleState state = (BattleState)GameInfo.getInstance().stateGame.getState(BattleState.BATTLE_STATE_ID);
        final Pokemon encounter = PokemonManager.getInstance().createPokemonByName("caterpie", 10); // I wonder which one it will be
        state.setBattle(new Battle(encounter));
        GameInfo.getInstance().stateGame.enterState(BattleState.BATTLE_STATE_ID, new FadeOutTransition(), new FadeInTransition());
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

        final LayerdMap map = World.getWorld().getLayerMap();
        if(!map.isBlocked(clickedTile)) {
            player.moveTo(clickedTile);
            return;
        }
        moveToNPC(map); // if is an NPC
        clickedTile = null;
	}
	private void moveToNPC(final LayerdMap map){
        final int npcID = map.getNPCidOn(clickedTile);
        final Person person = World.getWorld().findPersonById(npcID);
        if(person == null){
        	Log.debug("person null");
        	return;
        }
        final Tile facingTile = person.getFacingTile();
        final CommonGameState state = this;
        player.moveTo(new Tile(facingTile.multiply(new Vector2f(2,2))).plus(clickedTile));
        player.moveTo(facingTile.plus(clickedTile));
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

		camera.translateGraphics();
		if(clickedTile != null && !clickedTile.equals(player.getTile())) {
			g.setColor(new Color(0, 0, 255, 64));
			final Vector2f tl = clickedTile.topLeftPixels();
			g.fillRect(tl.x, tl.y, Tile.WIDTH, Tile.HEIGHT);
		}
		for(final IRenderable renderable : translatedViews){
			renderable.render(gc, g);
		}
		camera.untranslateGraphics();

		final Vector2f mouseTile = Tile.PixelsToGridPixels(mousePos);
		g.setColor(Color.black);
		g.drawRect(mouseTile.getX(), mouseTile.getY(), Tile.WIDTH, Tile.HEIGHT);
		super.render(gc, arg1, g);
	}

}
