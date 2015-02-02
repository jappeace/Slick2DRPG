package org.bakkes.game.controller.state.battle;

import java.util.List;
import java.util.Random;

import org.bakkes.game.controller.async.IThreadPool;
import org.bakkes.game.controller.init.scripting.PokemonModule;
import org.bakkes.game.controller.init.scripting.SpeciesModule;
import org.bakkes.game.controller.state.CommonGameState;
import org.bakkes.game.controller.state.battle.contestent.ContestentModule;
import org.bakkes.game.controller.state.battle.contestent.PlayerContestent;
import org.bakkes.game.controller.state.overworld.OverworldState;
import org.bakkes.game.model.GameInfo;
import org.bakkes.game.model.battle.move.IMove;
import org.bakkes.game.model.entity.player.Player;
import org.bakkes.game.model.pokemon.Pokemon;
import org.bakkes.game.view.battle.BattleLogView;
import org.bakkes.game.view.battle.PokeView;
import org.bakkes.game.view.components.LineWriter;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

public class BattleState extends CommonGameState {
	public static final int BATTLE_STATE_ID = 2;

	private Battle battle;
	private PlayerContestent playerContestent;
	private PokeView enemyView;
	private PokeView playerView;
	private BattleLogView battleLog;
	private int selectedMove = 0;
	private boolean firstRun = true;
	private @Inject Random random;
	private @Inject IThreadPool pool;
	private @Inject Player player;
	@Override
	public int getID() {
		return BATTLE_STATE_ID;
	}

	/*
	 * TODO: clear and use a module instead
	 */
	public void startWild() {
		Injector injector = Guice.createInjector(new SpeciesModule(GameInfo.WILD[random.nextInt(GameInfo.WILD.length)]));

		final Pokemon enemy = injector.getInstance(Pokemon.class);
		enemy.setLevel(GameInfo.WILD_POKE_LEVEL);

		final PokemonModule pokeModule = new PokemonModule(enemy);

		injector = Guice.createInjector(pokeModule);

		this.enemyView = injector.getInstance(PokeView.class);

		final Pokemon player = this.player.getPokebelt().getFirstAlive();
		pokeModule.setPokemon(player);
		this.playerView = injector.getInstance(PokeView.class);

		final ContestentModule contestent = new ContestentModule(player, enemy);
		injector = Guice.createInjector(contestent);

		this.playerContestent = injector.getInstance(PlayerContestent.class);
		injector = injector.createChildInjector(new BattleModule(this.playerContestent));
		this.battle = injector.getInstance(Battle.class);
		this.battleLog = injector.getInstance(BattleLogView.class);
		pool.execute(this.battle);
		firstRun = true;
	}
	/*
	 * TODO: make a menu view or something, this is retarded
	 */
	private void selectMove(final int selected) {
		final int moveSize = playerContestent.getOwn().getMoves().size();
		if(selected >= moveSize)
			selectedMove = 0;
		else if(selected < 0)
			selectedMove = moveSize - 1;
		else
			selectedMove = selected;
	}

	@Override
	public void init(final GameContainer gc, final StateBasedGame arg1)
			throws SlickException {
		super.init(gc, arg1);
	}

	@Override
	public void update(final GameContainer gc, final StateBasedGame arg1, final int delta)
			throws SlickException {
		if(firstRun) {
			gc.getInput().isKeyPressed(Input.KEY_ENTER);//reset ENTER key state
			firstRun = false;
		}
		if(battle.isOver()) {
			if(gc.getInput().isKeyPressed(Input.KEY_ENTER)) {
				selectedMove = 0;
				arg1.enterState(OverworldState.PLAYING_STATE_ID);
			}
			return;
		}

		if(gc.getInput().isKeyPressed(Input.KEY_DOWN)) {
			selectMove(selectedMove + 1);
		} else if(gc.getInput().isKeyPressed(Input.KEY_UP)) {
			selectMove(selectedMove - 1);
		}
		if(!gc.getInput().isKeyPressed(Input.KEY_ENTER)) {
			return;
		}
		playerContestent.selectMove(selectedMove);

		super.update(gc, arg1, delta);
	}
	private float leftOffset = 20f;
	private int showCount = 30;
	private @Inject LineWriter out;

	@Override
	public void render(final GameContainer gc, final StateBasedGame arg1, final Graphics g)
			throws SlickException {
		g.setColor(new Color(255, 255, 255, 255));
		g.setLineWidth(5f);
		out.clear();
		out.x(20);
		out.y(150);

		if(battle.isOver() && playerContestent.hasWon()) { //player won, don't show enemy stuff
			out.write("You are victorious! Press enter to leave");
		} else {
			enemyView.render(g);
		}

		if(battle.isOver() && !playerContestent.hasWon()) { //player lost, dont show player
			out.y(out.y() + 300);
			out.write("You lost! Press enter to leave");
			out.write("To heal your pokemon, visit the old lady at the beginning!");
		} else {

			final List<IMove> myMoves = playerContestent.getOwn().getMoves();
			for(int i = 0; i < myMoves.size(); i++) {
				if(i == selectedMove)
					g.setColor(new Color(255, 255, 255, 255));
				else
					g.setColor(new Color(255, 255, 255, 128));
				g.drawString(myMoves.get(i).getName() , leftOffset + 260f, 490f + (i * 15));
			}
            g.setColor(new Color(255, 255, 255, 255));
			playerView.render(g);
			battleLog.render(g);

		}
		out.render(g);

		super.render(gc, arg1, g);
	}
}
