package org.bakkes.game.model.entity;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.bakkes.game.GameInfo;
import org.bakkes.game.R;
import org.bakkes.game.World;
import org.bakkes.game.battle.Battle;
import org.bakkes.game.model.entity.command.ICommand;
import org.bakkes.game.model.entity.command.WalkPath;
import org.bakkes.game.model.entity.follower.FollowingPokemon;
import org.bakkes.game.model.map.Direction;
import org.bakkes.game.model.map.Tile;
import org.bakkes.game.scripting.interfaces.IPokemon;
import org.bakkes.game.state.BattleState;
import org.bakkes.game.state.OverworldState;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class Player extends Entity {
	private static Random random = new Random();

	private SpriteSheet _spriteSheet;
	private Animation[] _animation;

	protected IPokemon pokemon;
	private Inventory inventory;
	private OverworldState game;
	private LinkedList<ICommand> commands = new LinkedList<>();

	public Player(final OverworldState playingGameState) {
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

			setPosition(new Tile(8,8).topLeftPixels());
			inventory = new Inventory(this);
			final FollowingPokemon p = new FollowingPokemon(this);
			p.init(gc);
			pokemon = PokemonManager.getPokemonById(0);
		} catch (final SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addCommand(final ICommand command){
		commands.addLast(command);
	}
	@Override
	public void update(final GameContainer gc, final int delta) {
		if(isDone()) {
			return;
		}
		if(commands.getFirst().isDone()){
			commands.removeFirst();
		}
		if(isDone()) {
			return;
		}
		commands.getFirst().execute(delta/10f);



	}

	@Override
	public void render(final GameContainer gc, final Graphics g) {
		_animation[getFacing()].draw(getPosition().getX(), getPosition().getY());
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

	public void moveTo(final Tile toTile) {
        commands.add(new WalkPath(this, toTile));
	}

	public void clearCommands(){
		if(!isDone()){
			final ICommand first = commands.getFirst();
			first.onInterupt();
			final List<ICommand> retain = new LinkedList<ICommand>();
			retain.add(first);
            commands.retainAll(retain);
		}
	}

	public void onEnterNewTile(){
        if(World.getWorld().getLayerMap().isGrass(getTile()) && pokemon.get_health() > 0) {
            if(random.nextInt(20) == 1) { //1 in 20 chance to encounter pokemon
                final BattleState state = (BattleState)GameInfo.getInstance().stateGame.getState(BattleState.BATTLE_STATE_ID);
                final IPokemon encounter = PokemonManager.getPokemonById(random.nextInt(3));
                state.setBattle(new Battle(encounter));
                GameInfo.getInstance().stateGame.enterState(BattleState.BATTLE_STATE_ID, new FadeOutTransition(), new FadeInTransition());
            }
        }
        _animation[getFacing()].setAutoUpdate(true);
	}
	public void onFinishedWalking(){
        _animation[getFacing()].setAutoUpdate(false);
        _animation[getFacing()].setCurrentFrame(0);

	}
	public boolean isDone(){
		return commands.isEmpty();
	}

}
