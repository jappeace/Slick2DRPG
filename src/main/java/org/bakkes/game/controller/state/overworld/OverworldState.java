package org.bakkes.game.controller.state.overworld;

import java.util.Collection;
import java.util.Random;

import org.bakkes.game.controller.event.input.CompositeKeyListener;
import org.bakkes.game.controller.init.PlayerLoader;
import org.bakkes.game.controller.state.CommonGameState;
import org.bakkes.game.controller.state.battle.BattleState;
import org.bakkes.game.controller.state.overworld.command.MoveOnOverworld;
import org.bakkes.game.model.Bean;
import org.bakkes.game.model.entity.player.Player;
import org.bakkes.game.model.map.LayerdMap;
import org.bakkes.game.model.map.Tile;
import org.bakkes.game.view.IRenderable;
import org.bakkes.game.view.overworld.Camera;
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
	private @Inject PlayerLoader loader;


	private @Inject Bean<Tile> clickedTile;
	private @Inject Provider<MoveOnOverworld> onClickHandler;
	private @Inject Camera camera;

	private @Inject Provider<Collection<IRenderable>> translatedViews;
	private static final int WILD_POKE_CHANCE = 2; // chance of encountering wild pokemone (1 in chance)

	@Override
	public int getID() {
		return PLAYING_STATE_ID;
	}

	@Inject
	public OverworldState(final CompositeKeyListener listener){
		this.setKeyListener(listener);
	}

	@Override
	public void init(final GameContainer gc, final StateBasedGame arg1)
			throws SlickException {
		super.init(gc, arg1);
		map.load("outside");
		loader.load();
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
		for(final IRenderable renderable : translatedViews.get()){
			renderable.render(g);
		}
		camera.untranslateGraphics(g);

		final Vector2f mouseTile = Tile.PixelsToGridPixels(mousePos);
		g.setColor(Color.black);
		g.drawRect(mouseTile.getX(), mouseTile.getY(), Tile.WIDTH, Tile.HEIGHT);
		super.render(gc, arg1, g);
	}
}
