package org.bakkes.game.controller.state.battle;

import java.util.List;
import java.util.Random;

import org.bakkes.game.controller.async.IThreadPool;
import org.bakkes.game.controller.state.CommonGameState;
import org.bakkes.game.controller.state.State;
import org.bakkes.game.controller.state.battle.contestent.PlayerContestent;
import org.bakkes.game.model.battle.move.IMove;
import org.bakkes.game.model.entity.player.invetory.PokeBelt;
import org.bakkes.game.model.font.FontModule;
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
import com.google.inject.Provider;
import com.google.inject.name.Named;

public class BattleState extends CommonGameState {

	private Battle battle;
	private PokeView enemyView;
	private PokeView playerView;
	private BattleLogView battleLog;
	private int selectedMove = 0;
	private boolean firstRun = true;
	private @Inject PlayerContestent playerContestent;
	private @Inject Random random;
	private @Inject IThreadPool pool;
	private @Inject @Named("from player") PokeBelt player;
	private IBattleLoader loader;
	private @Inject Provider<WildLoader> wildLoader;

	public void setType(final BattleType to){
		if(to == BattleType.Wild){
			loader = wildLoader.get();
		}
	}

	@Override
	public int getID() {
		return State.Battle.ordinal();
	}

	@Override
	public void enter() {
		final Pokemon enemy = loader.getEnemy();
		final Pokemon player = this.player.getFirstAlive();

		playerContestent.setOwnPokemon(player);
		playerContestent.setTargetPokemon(enemy);
		final Injector injector = Guice.createInjector(new BattleModule(this.playerContestent), new FontModule());
		this.battle = injector.getInstance(Battle.class);
		this.battleLog = injector.getInstance(BattleLogView.class);
		pool.execute(this.battle);
		firstRun = true;
	}
	/*
	 * TODO: make a menu view or something, this is retarded
	 */
	private void selectMove(final int selected) {
		final int moveSize = playerContestent.getOwnPokemon().getMoves().size();
		if(selected >= moveSize)
			selectedMove = 0;
		else if(selected < 0)
			selectedMove = moveSize - 1;
		else
			selectedMove = selected;
	}

	@Inject Input input;
	@Inject StateBasedGame game;

	@Override
	public void update(final int delta) {
		if(firstRun) {
			input.isKeyPressed(Input.KEY_ENTER);//reset ENTER key state
			firstRun = false;
		}
		if(battle.isOver()) {
			if(input.isKeyPressed(Input.KEY_ENTER)) {
				selectedMove = 0;
				game.enterState(State.Overworld.ordinal());
			}
			return;
		}

		if(input.isKeyPressed(Input.KEY_DOWN)) {
			selectMove(selectedMove + 1);
		} else if(input.isKeyPressed(Input.KEY_UP)) {
			selectMove(selectedMove - 1);
		}
		if(!input.isKeyPressed(Input.KEY_ENTER)) {
			return;
		}
		playerContestent.selectMove(selectedMove);
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

			final List<IMove> myMoves = playerContestent.getOwnPokemon().getMoves();
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
