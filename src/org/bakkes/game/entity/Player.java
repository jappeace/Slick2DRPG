package org.bakkes.game.entity;

import java.util.ArrayList;
import java.util.Random;

import org.bakkes.game.Constants;
import org.bakkes.game.Game;
import org.bakkes.game.GameInfo;
import org.bakkes.game.World;
import org.bakkes.game.battle.Battle;
import org.bakkes.game.entity.follower.FollowingPokemon;
import org.bakkes.game.math.GridGraphicTranslator;
import org.bakkes.game.math.Vector2;
import org.bakkes.game.math.pathfinding.IPathFinder;
import org.bakkes.game.math.pathfinding.AStarPathFinder;
import org.bakkes.game.math.pathfinding.Node;
import org.bakkes.game.scripting.PokemonFactory;
import org.bakkes.game.scripting.interfaces.IPokemon;
import org.bakkes.game.state.BattleState;
import org.bakkes.game.state.PlayingGameState;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.util.pathfinding.Path;
import org.newdawn.slick.util.pathfinding.Path.Step;

public class Player extends Entity {
	private static Random random = new Random();
	
	private SpriteSheet _spriteSheet;
	private Animation[] _animation;
	
	protected IPokemon pokemon;
	protected FollowingPokemon follower;
	private boolean isCurrentlyMoving = false;
	private Path currentPath;
	private int currentStep;
	private float addedX, addedY;
	private int facing = Direction.SOUTH;
	private Inventory inventory;
	private PlayingGameState game;
	
	public ArrayList<Node> considered; 
	public ArrayList<Node> usedPath;
	
	public Player(PlayingGameState playingGameState) {
		game = playingGameState;
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
			
			for(int i = 0; i <= _animation.length - 1; i++) {
				_animation[i].setPingPong(true);
				_animation[i].setAutoUpdate(false);
			}
			
			position = GridGraphicTranslator.GridToPixels(new Vector2(8, 8));
			inventory = new Inventory(this);
			FollowingPokemon p = new FollowingPokemon(this);
			p.init(gc);
			this.follower = p;
			pokemon = PokemonManager.getPokemonById(0);
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

			if(destinationPixelPoint.getX() < position.getX())
				dX = -dX;
			
			if(destinationPixelPoint.getY() < position.getY())
				dY = -dY;
			
			
			if(Math.abs(addedX + dX) >= Constants.TILE_WIDTH) {
				dX = dX >= 0 ? Constants.TILE_WIDTH + 0.01f- addedX : -Constants.TILE_WIDTH - 0.01f - addedX;
			}
			
			if(Math.abs(addedY + dY) >= Constants.TILE_HEIGHT) {
				dY = dY >= 0 ? Constants.TILE_HEIGHT + 0.01f - addedY : -Constants.TILE_HEIGHT - 0.01f - addedY;
			}

			addedX += dX;
			addedY += dY;
			
			position.add(new Vector2(dX, dY));
			
			
			if(GridGraphicTranslator.PixelsInTile(position, destinationPoint)) {
				follower.stepsTaken++;
				position = GridGraphicTranslator.PixelsToGridPixels(position);
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
					if(World.getWorld().getLayerMap().isGrass(new Vector2(nextStep.getX(), nextStep.getY())) && pokemon.get_health() > 0) {
						if(random.nextInt(20) == 1) { //1 in 20 chance to encounter pokemon
							BattleState state = (BattleState)GameInfo.getInstance().stateGame.getState(BattleState.BATTLE_STATE_ID);
							IPokemon encounter = PokemonManager.getPokemonById(random.nextInt(3));
							state.setBattle(new Battle(encounter));
							GameInfo.getInstance().stateGame.enterState(BattleState.BATTLE_STATE_ID, new FadeOutTransition(), new FadeInTransition());
						}
					}
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
					if(follower != null)
						follower.face(facing);
				}
			}
		}
		if(follower != null)
			follower.update(delta);
	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		_animation[facing].draw(position.getX(), position.getY());
		if(GameInfo.SHOW_DEBUG_INFO) {
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
		if(follower != null)
			follower.render(gc, g);
	}
	
	public Vector2 getGridPosition() {
		return GridGraphicTranslator.PixelsToGrid(position);
	}
	
	public Vector2 getPixelPosition() {
		return position;
	}
	
	public IPokemon getPokemon() {
		return pokemon;
	}
	
	public Inventory getInventory() {
		return inventory;
	}
	
	public void showDialog(String text) {
		System.out.println("Attempting to show dialog: " + text);
		game.showDialog(text);
		game.activateDialogs();
	}
	
	public boolean isCurrentlyMoving() {
		return isCurrentlyMoving;
	}
	
	public void moveTo(Vector2 toTile) {
		IPathFinder pathFinder = new AStarPathFinder(World.getWorld().getLayerMap());
		Path path = pathFinder.getShortestPath(getGridPosition(), toTile);
		this.considered = ((AStarPathFinder)pathFinder).considered;
		this.usedPath = ((AStarPathFinder)pathFinder).thePath;
		if(path != null)
			Move(path);
	}

	public FollowingPokemon getFollower() {
		return follower;
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
