package org.bakkes.game.entity;

import java.util.ArrayList;

import org.bakkes.game.Constants;
import org.bakkes.game.Game;
import org.bakkes.game.World;
import org.bakkes.game.math.GridGraphicTranslator;
import org.bakkes.game.math.Vector2;
import org.bakkes.game.math.pathfinding.IPathFinder;
import org.bakkes.game.math.pathfinding.AStarPathFinder;
import org.bakkes.game.math.pathfinding.Node;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.util.pathfinding.Path;
import org.newdawn.slick.util.pathfinding.Path.Step;

public class Player extends Entity {

	private Vector2 _pixelPosition;
	private SpriteSheet _spriteSheet;
	private Animation[] _animation;
	
	private boolean isCurrentlyMoving = false;
	private Path currentPath;
	private int currentStep;
	private float addedX, addedY;
	private int facing = Direction.NORTH;
	private Inventory inventory;
	private Game game;
	
	public ArrayList<Node> considered; 
	public ArrayList<Node> usedPath;
	
	public Player(Game g) {
		game = g;
	}
	
	@Override
	public void init(GameContainer gc) {
		try {
			
			_spriteSheet = new SpriteSheet("res/sprites/player.png", 32, 32);
			_animation = new Animation[4];
			_animation[Direction.NORTH] = new Animation(_spriteSheet, 0, 0, 2, 0, true, 200, true);
			_animation[Direction.EAST]  = new Animation(_spriteSheet, 0, 3, 2, 3, true, 200, true);
			_animation[Direction.SOUTH] = new Animation(_spriteSheet, 0, 1, 2, 1, true, 200, true);
			_animation[Direction.WEST]  = new Animation(_spriteSheet, 0, 2, 2, 2, true, 200, true);
			
			for(int i = 0; i <= _animation.length; i++) {
				_animation[i].setPingPong(true);
				_animation[i].setAutoUpdate(false);
			}
			
			_pixelPosition = GridGraphicTranslator.GridToPixels(new Vector2(8, 8));
			inventory = new Inventory(this);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void update(GameContainer gc, int delta) {
		if(isCurrentlyMoving) {
			Step s = currentPath.getStep(currentStep);
			Vector2 destinationPoint = new Vector2(s.getX(), s.getY());
			Vector2 destinationPixelPoint = GridGraphicTranslator.GridToPixels(destinationPoint);
			float dX = delta / 10f;
			float dY = delta / 10f;

			if(destinationPixelPoint.getX() < _pixelPosition.getX())
				dX = -dX;
			
			if(destinationPixelPoint.getY() < _pixelPosition.getY())
				dY = -dY;
			
			
			if(Math.abs(addedX + dX) >= Constants.TILE_WIDTH) {
				dX = dX >= 0 ? Constants.TILE_WIDTH - addedX : -Constants.TILE_WIDTH - addedX;
			}
			
			if(Math.abs(addedY + dY) >= Constants.TILE_HEIGHT) {
				dY = dY >= 0 ? Constants.TILE_HEIGHT - addedY : -Constants.TILE_HEIGHT - addedY;
			}

			addedX += dX;
			addedY += dY;
			
			_pixelPosition.add(new Vector2(dX, dY));
			
			
			if(GridGraphicTranslator.PixelsInTile(_pixelPosition, destinationPoint)) {
				_pixelPosition = GridGraphicTranslator.PixelsToGridPixels(_pixelPosition);
				currentStep++;
				addedX = 0;
				addedY = 0;

				if(currentStep >= currentPath.getLength()) {
					isCurrentlyMoving = false;
					currentStep = 0;
					_animation[facing].setAutoUpdate(false);
					_animation[facing].setCurrentFrame(0);
				} else {
					Step nextStep = currentPath.getStep(currentStep);
					Vector2 p = new Vector2(nextStep.getX(), nextStep.getY()).minusOperator(getGridPosition());
					if(p.getX() == 1)
						facing = Direction.EAST;
					else if(p.getX() == -1)
						facing = Direction.WEST;
					
					if(p.getY() == 1)
						facing = Direction.SOUTH;
					else if(p.getY() == -1)
						facing = Direction.NORTH;
					
					_animation[facing].setAutoUpdate(true);
				}
			}
		}
	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		_animation[facing].draw(_pixelPosition.getX(), _pixelPosition.getY());
		if(considered != null && isCurrentlyMoving) {
			g.setColor(new Color(0, 255, 255, 64));
			for(Node node : considered) {
				Vector2 pos = node.getPosition();
				g.fillRect(pos.getX() * 16, pos.getY() * 16, Constants.TILE_WIDTH, Constants.TILE_HEIGHT);
			}
			g.setColor(new Color(255, 255, 255, 64));
			for(Node node : usedPath) {
				Vector2 pos = node.getPosition();
				g.fillRect(pos.getX() * 16, pos.getY() * 16, Constants.TILE_WIDTH, Constants.TILE_HEIGHT);
			}
		}
	}
	
	public Vector2 getGridPosition() {
		return GridGraphicTranslator.PixelsToGrid(_pixelPosition);
	}
	
	public Vector2 getPixelPosition() {
		return _pixelPosition;
	}
	
	public Inventory getInventory() {
		return inventory;
	}
	
	public void showDialog(String text) {
		game.showDialog(text);
	}
	
	public void moveTo(Vector2 toTile) {
		IPathFinder pathFinder = new AStarPathFinder(World.getWorld().getLayerMap());
		Path path = pathFinder.getShortestPath(getGridPosition(), toTile);
		this.considered = ((AStarPathFinder)pathFinder).considered;
		this.usedPath = ((AStarPathFinder)pathFinder).thePath;
		if(path != null)
			Move(path);
	}

	public void Move(Path p) {
		isCurrentlyMoving = true;
		currentPath = p;
		currentStep = 0;
	}

	public int getDirection() {
		// TODO Auto-generated method stub
		return facing;
	}
	
	public int getFacingNPC() {
		TiledMap map = World.getWorld().getMap();
		int layerIndex = map.getLayerIndex("npc");
		Vector2 diff = getGridPosition().copy();
		switch(getDirection()) {
		case Direction.NORTH:
			diff.addY(-1);
			break;
		case Direction.EAST:
			diff.addX(2);
			break;
		case Direction.SOUTH:
			diff.addY(2);
			break;
		case Direction.WEST:
			diff.addX(-2);
			diff.addY(1);
			break;
		}
		int tileId = map.getTileId((int)diff.getX(), (int)diff.getY(), layerIndex);
		if(tileId == 0)
			return -1;
		return Integer.parseInt(map.getTileProperty(tileId, "npcid", "-1"));
	}
}
