package org.bakkes.game;

import org.bakkes.game.entity.Player;
import org.bakkes.game.map.LayerBasedMap;
import org.bakkes.game.math.GridGraphicTranslator;
import org.bakkes.game.math.Vector2;
import org.bakkes.game.scripting.ScriptManager;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.util.pathfinding.AStarPathFinder;
import org.newdawn.slick.util.pathfinding.Path;

public class Game extends BasicGame {

	private Player player;
	private TiledMap map;
	private LayerBasedMap layerMap;
	private Camera camera;
	private Vector2 destinationTile;
	public Game(String title) {
		super(title);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		player = new Player();
		player.init(gc);
		map = new TiledMap("res/map/map.tmx");
		layerMap = new LayerBasedMap(map, map.getLayerIndex("objects"));
		camera = new Camera(gc, map);
		ScriptManager.loadScripts();
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		
		Input input = gc.getInput();
		
		if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			Vector2 mousePos = new Vector2(input.getMouseX(), input.getMouseY());
			mousePos = new Vector2(mousePos.getX() + camera.cameraX, mousePos.getY() + camera.cameraY);
			destinationTile = GridGraphicTranslator.PixelsToGrid(mousePos);
			
			if(!layerMap.blocked(null, (int)destinationTile.getX(), (int)destinationTile.getY())) {
				AStarPathFinder pathFinder = new AStarPathFinder(layerMap, 500, false);
				Path path = pathFinder.findPath(null, (int)player.getGridPosition().getX(), (int)player.getGridPosition().getY(), 
						(int)destinationTile.getX(), (int)destinationTile.getY());
				if(path != null)
					player.Move(path);
				else
					destinationTile = null;
			} else {
				destinationTile = null;
			}
		}
		
		player.update(gc, delta);
	}
	
	public void render(GameContainer gc, Graphics g) throws SlickException {
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
	}

}
