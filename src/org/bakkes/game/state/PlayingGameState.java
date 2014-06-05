package org.bakkes.game.state;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import org.bakkes.game.Camera;
import org.bakkes.game.Constants;
import org.bakkes.game.GameInfo;
import org.bakkes.game.World;
import org.bakkes.game.entity.Player;
import org.bakkes.game.entity.follower.state.StateMachine;
import org.bakkes.game.events.ChangeToBirdListener;
import org.bakkes.game.events.GameKeyListener;
import org.bakkes.game.events.InventoryToggleListener;
import org.bakkes.game.events.MovementListener;
import org.bakkes.game.events.ScriptReloadListener;
import org.bakkes.game.events.TalkToNPCListener;
import org.bakkes.game.math.GridGraphicTranslator;
import org.bakkes.game.math.Vector2;
import org.bakkes.game.scripting.ScriptManager;
import org.bakkes.game.ui.DialogBox;
import org.bakkes.game.ui.DrawableGameComponent;
import org.bakkes.game.ui.InventoryGameComponent;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class PlayingGameState extends CommonGameState {
	public static final int PLAYING_STATE_ID = 0;
	public static boolean SHOW_DEBUG_INFO = true;
	
	private Player player;
	private Camera camera;
	private Vector2 destinationTile;

	public Player getPlayer() {
		return player;
	}

	@Override
	public int getID() {
		return PLAYING_STATE_ID;
	}

	public void init(GameContainer gc, StateBasedGame arg1)
			throws SlickException {
		super.init(gc, arg1);
		ScriptManager.loadScripts();
		player = new Player(this);
		player.init(gc);
		GameInfo.getInstance().player = player;
		camera = new Camera(gc, World.getWorld().getMap());
		
		addKeyListener(new TalkToNPCListener(this));
		addKeyListener(new ScriptReloadListener());
		addKeyListener(new MovementListener(this));
		addKeyListener(new InventoryToggleListener(this, new InventoryGameComponent(player)));
		addKeyListener(new ChangeToBirdListener(arg1));
	}

	public void update(GameContainer gc, StateBasedGame arg1, int delta)
			throws SlickException {
		super.update(gc, arg1, delta);
		Input input = gc.getInput();
		
		if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON) && inputEnabled) {
			Vector2 mousePos = new Vector2(input.getMouseX(), input.getMouseY());
			mousePos = new Vector2(mousePos.getX() + camera.cameraX, mousePos.getY() + camera.cameraY);
			destinationTile = GridGraphicTranslator.PixelsToGrid(mousePos);
			
			if(!World.getWorld().getLayerMap().isBlocked(destinationTile)) {
				player.moveTo(destinationTile);
			} else {
				destinationTile = null;
			}
		}
		
		player.update(gc, delta);
	}
	

	public void render(GameContainer gc, StateBasedGame arg1, Graphics g)
			throws SlickException {
		
		Input input = gc.getInput();
		Vector2 mousePos = new Vector2(input.getMouseX(), input.getMouseY());
		Vector2 paintPos = GridGraphicTranslator.PixelsToGridPixels(mousePos);
		
		camera.centerOn((int)player.getPixelPosition().getX(), (int)player.getPixelPosition().getY());
		camera.drawMap();
		
		camera.translateGraphics();
		if(destinationTile != null && destinationTile != player.getGridPosition()) {
			g.setColor(new Color(0, 0, 255, 64));
			g.fillRect(destinationTile.getX() * 16, destinationTile.getY() * 16, Constants.TILE_WIDTH, Constants.TILE_HEIGHT);
		}
		player.render(gc, g);
		camera.untranslateGraphics();
		
		g.setColor(Color.black);
		g.drawRect(paintPos.getX(), paintPos.getY(), Constants.TILE_WIDTH, Constants.TILE_HEIGHT);
		if(SHOW_DEBUG_INFO) {
			StateMachine stateMachine = player.getFollower().getStateMachine();
			if(stateMachine.getState() != null) {
				g.setColor(Color.white);
				g.drawString("Pokemon state: " + stateMachine.getState().getName(), 10, 50);
			}
		}
		super.render(gc, arg1, g);
	}

}
