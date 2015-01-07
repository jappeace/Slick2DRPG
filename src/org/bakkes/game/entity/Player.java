package org.bakkes.game.entity;

import java.util.Random;

import org.bakkes.game.Constants;
import org.bakkes.game.GameInfo;
import org.bakkes.game.World;
import org.bakkes.game.battle.Battle;
import org.bakkes.game.entity.follower.FollowingPokemon;
import org.bakkes.game.math.GridGraphicTranslator;
import org.bakkes.game.scripting.interfaces.IPokemon;
import org.bakkes.game.state.BattleState;
import org.bakkes.game.state.PlayingGameState;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.util.pathfinding.AStarPathFinder;
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

	public Player(final PlayingGameState playingGameState) {
		game = playingGameState;
	}

	@Override
	public void init(final GameContainer gc) {
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

			position = GridGraphicTranslator.GridToPixels(new Vector2f(8, 8));
			inventory = new Inventory(this);
			final FollowingPokemon p = new FollowingPokemon(this);
			p.init(gc);
			this.follower = p;
			pokemon = PokemonManager.getPokemonById(0);
		} catch (final SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void update(final GameContainer gc, final int delta) {
		if(isCurrentlyMoving) {
			final Step s = currentPath.getStep(currentStep);
			final Vector2f destinationPoint = new Vector2f(s.getX(), s.getY());
			final Vector2f destinationPixelPoint = GridGraphicTranslator.GridToPixels(destinationPoint);
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

			position.add(new Vector2f(dX, dY));


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
					final Step nextStep = currentPath.getStep(currentStep);
					if(World.getWorld().getLayerMap().isGrass(new Vector2f(nextStep.getX(), nextStep.getY())) && pokemon.get_health() > 0) {
						if(random.nextInt(20) == 1) { //1 in 20 chance to encounter pokemon
							final BattleState state = (BattleState)GameInfo.getInstance().stateGame.getState(BattleState.BATTLE_STATE_ID);
							final IPokemon encounter = PokemonManager.getPokemonById(random.nextInt(3));
							state.setBattle(new Battle(encounter));
							GameInfo.getInstance().stateGame.enterState(BattleState.BATTLE_STATE_ID, new FadeOutTransition(), new FadeInTransition());
						}
					}
					final Vector2f p = new Vector2f(nextStep.getX(), nextStep.getY()).sub(getGridPosition());
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
	public void render(final GameContainer gc, final Graphics g) {
		_animation[facing].draw(position.getX(), position.getY());
		if(follower != null)
			follower.render(gc, g);
	}

	public Vector2f getGridPosition() {
		return GridGraphicTranslator.PixelsToGrid(position);
	}

	public Vector2f getPixelPosition() {
		return position;
	}

	public IPokemon getPokemon() {
		return pokemon;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void showDialog(final String text) {
		System.out.println("Attempting to show dialog: " + text);
		game.showDialog(text);
		game.activateDialogs();
	}

	public boolean isCurrentlyMoving() {
		return isCurrentlyMoving;
	}

	public void moveTo(final Vector2f toTile) {
		final AStarPathFinder pathFinder = new AStarPathFinder(World.getWorld().getLayerMap(), 100, false);
		final Path path = pathFinder.findPath(null,(int)getGridPosition().x,(int) getGridPosition().y,(int) toTile.x, (int)toTile.y);
		if(path != null)
			Move(path);
	}

	public FollowingPokemon getFollower() {
		return follower;
	}

	public void Move(final Path p) {
		isCurrentlyMoving = true;
		currentPath = p;
		currentStep = 0;
	}

	public int getDirection() {
		// TODO Auto-generated method stub
		return facing;
	}

	public int getFacingNPC() {
		final TiledMap map = World.getWorld().getMap();
		final int layerIndex = map.getLayerIndex("npc");
		final Vector2f diff = getGridPosition().copy();
		switch(getDirection()) {
		case Direction.NORTH:
			diff.add(new Vector2f(0,-1));
			break;
		case Direction.EAST:
			diff.add(new Vector2f(2,0));
			break;
		case Direction.SOUTH:
			diff.add(new Vector2f(0,2));
			break;
		case Direction.WEST:
			diff.add(new Vector2f(-2,1));
			break;
		}
		final int tileId = map.getTileId((int)diff.getX(), (int)diff.getY(), layerIndex);
		if(tileId == 0)
			return -1;
		return Integer.parseInt(map.getTileProperty(tileId, "npcid", "-1"));
	}
}
