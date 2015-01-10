package org.bakkes.game.entity;

import java.util.Random;

import org.bakkes.game.GameInfo;
import org.bakkes.game.R;
import org.bakkes.game.World;
import org.bakkes.game.battle.Battle;
import org.bakkes.game.entity.follower.FollowingPokemon;
import org.bakkes.game.map.Tile;
import org.bakkes.game.scripting.interfaces.IPokemon;
import org.bakkes.game.state.BattleState;
import org.bakkes.game.state.PlayingGameState;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.util.Log;
import org.newdawn.slick.util.pathfinding.Path;

public class Player extends Entity {
	private static Random random = new Random();

	private SpriteSheet _spriteSheet;
	private Animation[] _animation;

	protected IPokemon pokemon;
	protected FollowingPokemon follower;
	private boolean isMoving = false;
	private Path currentPath;
	private int currentStep;
	private Vector2f added = new Vector2f();
	private int facing = Direction.SOUTH;
	private Inventory inventory;
	private PlayingGameState game;

	public Player(final PlayingGameState playingGameState) {
		game = playingGameState;
	}

	@Override
	public void init(final GameContainer gc) {
		try {

			_spriteSheet = new SpriteSheet(R.sprites+"player.png", 32, 32);

			_animation = new Animation[4];
			_animation[Direction.NORTH] = new Animation(_spriteSheet, 0, 0, 2, 0, true, 200, true);
			_animation[Direction.EAST]  = new Animation(_spriteSheet, 0, 3, 2, 3, true, 200, true);
			_animation[Direction.SOUTH] = new Animation(_spriteSheet, 0, 1, 2, 1, true, 200, true);
			_animation[Direction.WEST]  = new Animation(_spriteSheet, 0, 2, 2, 2, true, 200, true);

			for(int i = 0; i <= _animation.length - 1; i++) {
				_animation[i].setPingPong(true);
				_animation[i].setAutoUpdate(false);
			}

			position = new Tile(30, 30).topLeftPixels();
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
		if(!isMoving) {
			return;
		}
		move(delta/10f);
		if(follower != null)
			follower.update(delta);
	}
	private void move(final float tpf){
        final Tile destinationTile = new Tile(currentPath.getStep(currentStep));

        final Vector2f delta = destinationTile.minus(getTile()).multiply(new Vector2f(tpf, tpf));
        if(delta.equals(new Vector2f(0,0))){
            if(destinationTile.topLeftPixels().x < position.x){
                delta.x = -tpf;
            }
            if(destinationTile.topLeftPixels().y < position.y){
                delta.y = -tpf;
            }
        }

        boolean arrived = false;
        if(Math.abs(added.x + delta.x) >= Tile.WIDTH) {
        	final float smoothDistance = Tile.WIDTH - Math.abs(added.x) +0.01f;
            delta.x = delta.x >= 0 ?  smoothDistance: -smoothDistance;
        	Log.debug("back you: " +delta);
            arrived = true;
        }

        if(Math.abs(added.y + delta.y) >= Tile.HEIGHT) {
        	final float smoothDistance = Tile.HEIGHT - Math.abs(added.y) +0.01f;
            delta.y = delta.y >= 0 ? smoothDistance : -smoothDistance;
        	Log.debug("back you: " +delta);
            arrived = true;
        }

        added.add(delta);
        position.add(delta);

        Log.debug("moving to next tile: " + destinationTile + " with the following speed: " + delta);
        if(delta.x < 0 || delta.y < 0){
        	if(!arrived){
        		return;
        	}
        }
        if(!destinationTile.contains(position)) {

        	return;
        }

        Log.debug("has arrived: " + arrived);
        Log.debug(destinationTile.toString());
        Log.debug(position.toString());
        follower.stepsTaken++;
        position = Tile.PixelsToGridPixels(position);
        currentStep++;
        added.x = 0;
        added.y = 0;

        if(currentStep >= currentPath.getLength()) {
            isMoving = false;
            currentStep = 0;
            _animation[facing].setAutoUpdate(false);
            _animation[facing].setCurrentFrame(0);
            return;
        }

        final Tile nextTile = new Tile(currentPath.getStep(currentStep));
        if(World.getWorld().getLayerMap().isGrass(nextTile) && pokemon.get_health() > 0) {
            if(random.nextInt(20) == 1) { //1 in 20 chance to encounter pokemon
                final BattleState state = (BattleState)GameInfo.getInstance().stateGame.getState(BattleState.BATTLE_STATE_ID);
                final IPokemon encounter = PokemonManager.getPokemonById(random.nextInt(3));
                state.setBattle(new Battle(encounter));
                GameInfo.getInstance().stateGame.enterState(BattleState.BATTLE_STATE_ID, new FadeOutTransition(), new FadeInTransition());
            }
        }
        if(delta.x != 0){
            if(delta.x < 0){
                facing = Direction.WEST;
            }else {
                facing = Direction.EAST;
            }
        }
        if(delta.y != 0){
        	if(delta.y < 0){
        		facing = Direction.NORTH;
        	}else{
        		facing = Direction.SOUTH;
        	}
        }

        _animation[facing].setAutoUpdate(true);
        if(follower != null)
            follower.face(facing);
	}

	@Override
	public void render(final GameContainer gc, final Graphics g) {
		_animation[facing].draw(position.getX(), position.getY());
		if(follower != null)
			follower.render(gc, g);
		if(GameInfo.SHOW_DEBUG_INFO && isMoving){

			g.setColor(new Color(255, 255, 255, 64));
			for(int i = 0; i < currentPath.getLength(); i++){
				final Tile tile = new Tile(currentPath.getStep(i));
				g.fillRect(tile.topLeftPixels().x, tile.topLeftPixels().y, Tile.WIDTH, Tile.HEIGHT);
			}
		}
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
		return isMoving;
	}

	public void moveTo(final Tile toTile) {
		final Path path = World.getPathFinder().findPath(null,getTile().left,getTile().top,toTile.left, toTile.top);
		if(path != null)
			Move(path);
	}

	public FollowingPokemon getFollower() {
		return follower;
	}

	public void Move(final Path p) {
		isMoving = true;
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
		final Vector2f diff = getTile().toVector();
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
