package org.bakkes.game.state;

import org.bakkes.game.Camera;
import org.bakkes.game.GameInfo;
import org.bakkes.game.World;
import org.bakkes.game.entity.Player;
import org.bakkes.game.entity.follower.state.StateMachine;
import org.bakkes.game.events.key.InventoryToggleListener;
import org.bakkes.game.events.key.MovementListener;
import org.bakkes.game.events.key.ScriptReloadListener;
import org.bakkes.game.events.key.TalkToNPCListener;
import org.bakkes.game.map.LayerdMap;
import org.bakkes.game.map.Tile;
import org.bakkes.game.scripting.ScriptManager;
import org.bakkes.game.ui.InventoryGameComponent;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

public class OverworldState extends CommonGameState {
	public static final int PLAYING_STATE_ID = 0;

	private Player player;
	private Camera camera;
	private Tile clickedTile;

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
		super.init(gc, arg1);
		ScriptManager.loadScripts();
		player = new Player(this);
		player.init(gc);
		GameInfo.getInstance().player = player;
		GameInfo.getInstance().stateGame = arg1;
		camera = new Camera(gc);

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
	}
	private void handleMouseInput(final Input input){
		if(!input.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
			return;
		}
        Vector2f mousePos = new Vector2f(input.getMouseX(), input.getMouseY());
        mousePos = new Vector2f(mousePos.getX() + camera.cameraX, mousePos.getY() + camera.cameraY);
        clickedTile = Tile.createFromPixelsCoordinates(mousePos);

        final LayerdMap map = World.getWorld().getLayerMap();
        if(!map.isBlocked(clickedTile)) {
            player.moveTo(clickedTile);
            return;
        }

        final int npcID = map.getNPCidOn(clickedTile);
        /*
         * and here I found out the scripting world has no proper way of interacting
         * with java, so I'll replace python a bit sooner than planned
         */
        clickedTile = null;
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
		player.render(gc, g);
		camera.untranslateGraphics();

		final Vector2f mouseTile = Tile.PixelsToGridPixels(mousePos);
		g.setColor(Color.black);
		g.drawRect(mouseTile.getX(), mouseTile.getY(), Tile.WIDTH, Tile.HEIGHT);
		if(GameInfo.SHOW_DEBUG_INFO) {
			final StateMachine stateMachine = player.getFollower().getStateMachine();
			if(stateMachine.getState() != null) {
				g.setColor(Color.white);
				g.drawString("Pokemon state: " + stateMachine.getState().getName(), 10, 50);
			}
		}
		super.render(gc, arg1, g);
	}

}
