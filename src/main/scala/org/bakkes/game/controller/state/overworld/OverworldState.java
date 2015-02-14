package org.bakkes.game.controller.state.overworld;

import java.util.Collection;
import java.util.Random;

import org.bakkes.game.controller.MessageBoxController;
import org.bakkes.game.controller.event.input.CompositeKeyListener;
import org.bakkes.game.controller.init.PlayerLoader;
import org.bakkes.game.controller.state.CommonGameState;
import org.bakkes.game.controller.state.State;
import org.bakkes.game.controller.state.StateManager;
import org.bakkes.game.controller.state.battle.BattleState;
import org.bakkes.game.controller.state.battle.BattleType;
import org.bakkes.game.controller.state.overworld.command.MoveOnOverworld;
import org.bakkes.game.controller.state.overworld.command.Teleport;
import org.bakkes.game.model.Bean;
import org.bakkes.game.model.entity.player.Player;
import org.bakkes.game.model.entity.player.invetory.PokeBelt;
import org.bakkes.game.model.map.LayerdMap;
import org.bakkes.game.model.map.Tile;
import org.bakkes.game.model.pokemon.Pokemon;
import org.bakkes.game.view.IRenderable;
import org.bakkes.game.view.overworld.Camera;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;

public class OverworldState extends CommonGameState {
	private @Inject Player player;
	private @Inject Random random;
	private @Inject LayerdMap map;
	private @Inject PlayerLoader loader;

	private @Inject Input input;
	private @Inject StateManager states;

	private @Inject Bean<Tile> clickedTile;
	private @Inject Provider<MoveOnOverworld> onClickHandler;
	private @Inject Camera camera;

	private @Inject Provider<Collection<IRenderable>> translatedViews;
	private static final int WILD_POKE_CHANCE = 2; // chance of encountering wild pokemone (1 in chance)


	private @Inject @Named("from player") PokeBelt belt;
	private @Inject MessageBoxController msgBoxController;
	private @Inject Provider<Teleport> teleportProvider;
	@Override
	public int getID() {
		return State.Overworld.ordinal();
	}

	@Inject
	public OverworldState(final CompositeKeyListener listener){
		this.setKeyListener(listener);
	}

	@Override
	public void init(){
		map.load("outside");
		loader.load();
	}
	@Override
	public void enter(){
		if(belt.isEmpty()){
			// player has no pokemon, is normal at start of game, so no respawn check
			return;
		}
		if(belt.getFirstAlive() == null){
			// dead now, respawn
			player.clearCommands();
			player.addCommand(
                teleportProvider.get()
                    .setSubject(player)
                    .setTo(loader.getStartLocation())
            );
			for(final Pokemon poke : belt){
				poke.heal();
			}
			msgBoxController.add("GOD", "You died, and like all things in this world get a respawn, but ONLY 1");
		}
	}

	@Override
	public void update(final int delta) {
		if(inputEnabled){
			handleMouseInput();
		}
		player.update(delta);
		checkForWildPokeBattle();
	}
	private void checkForWildPokeBattle(){
		if(!player.hasEnteredNewTile()){
			return;
		}
        if(!map.isGrass(player.getTile())) {
        	return;
        }

        if(random.nextInt(WILD_POKE_CHANCE) != 1) {
        	return;
        }
        final BattleState state = ((BattleState)states.get(State.Battle));
        state.setType(BattleType.Wild);
        states.enter(State.Battle);
	}
	private void handleMouseInput(){
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
        clickedTile.setData(Tile.createFromPixelsCoordinates(mousePos));
        player.addCommand(onClickHandler.get());
	}

	@Override
	public void render(final GameContainer gc, final StateBasedGame arg1, final Graphics g)
			throws SlickException {


		final Input input = gc.getInput();
		final Vector2f mousePos = new Vector2f(input.getMouseX(), input.getMouseY());

		camera.centerOn((int)player.getPosition().getX(), (int)player.getPosition().getY());
		camera.drawMap();

		camera.translateGraphics(g);
		if(clickedTile.getData() != null && !clickedTile.getData().equals(player.getTile())) {
			g.setColor(new Color(0, 0, 255, 64));
			final Vector2f tl = clickedTile.getData().topLeftPixels();
			g.fillRect(tl.x, tl.y, Tile.WIDTH, Tile.HEIGHT);
		}
		for(final IRenderable renderable: translatedViews.get()){
			renderable.render(g);
		}
		camera.untranslateGraphics(g);

		final Vector2f mouseTile = Tile.PixelsToGridPixels(mousePos);
		g.setColor(Color.black);
		g.drawRect(mouseTile.getX(), mouseTile.getY(), Tile.WIDTH, Tile.HEIGHT);
		super.render(gc, arg1, g);
	}
}
