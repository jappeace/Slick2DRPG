package org.bakkes.game.state;

import java.util.List;

import org.bakkes.game.GameInfo;
import org.bakkes.game.battle.BattleMaster;
import org.bakkes.game.battle.BattleMasterModule;
import org.bakkes.game.battle.contestent.ContestentModule;
import org.bakkes.game.battle.contestent.PlayerContestent;
import org.bakkes.game.model.battle.Turn;
import org.bakkes.game.model.battle.move.IMove;
import org.bakkes.game.model.pokemon.Pokemon;
import org.bakkes.game.scripting.PokemonModule;
import org.bakkes.game.scripting.SpeciesModule;
import org.bakkes.game.view.LineWriterView;
import org.bakkes.game.view.PokeView;
import org.bakkes.game.view.PositionModule;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class BattleState extends CommonGameState {
	public static final int BATTLE_STATE_ID = 2;

	private BattleMaster battle;
	private PlayerContestent player;
	private PokeView enemyView;
	private PokeView playerView;
	private int selectedMove = 0;
	private boolean firstRun = true;
	@Override
	public int getID() {
		return BATTLE_STATE_ID;
	}

	/*
	 * TODO: clear and use a module instead
	 */
	public void startWild() {
		Injector injector = Guice.createInjector(new SpeciesModule(10, GameInfo.WILD[GameInfo.RANDOM.nextInt(GameInfo.WILD.length)]));

		final Pokemon enemy = injector.getInstance(Pokemon.class);
		final PokemonModule pokeModule = new PokemonModule(enemy);
		final PositionModule positionModule = new PositionModule(new Vector2f(20,10));

		injector = Guice.createInjector(positionModule, pokeModule);

		this.enemyView = injector.getInstance(PokeView.class);

		final Pokemon player = GameInfo.getInstance().player.getPokemon();
		pokeModule.setPokemon(player);
		positionModule.setPosition(new Vector2f(20f, 400));
		this.playerView = injector.getInstance(PokeView.class);

		final ContestentModule contestent = new ContestentModule(player, enemy);
		injector = Guice.createInjector(contestent);

		this.player = injector.getInstance(PlayerContestent.class);
		injector = injector.createChildInjector(new BattleMasterModule(this.player));
		this.battle = injector.getInstance(BattleMaster.class);

		new Thread(this.battle).start();
		this.playerView.renderMoves = false;
		firstRun = true;
	}
	/*
	 * TODO: delete and fix with proper dependecies
	 */
	private List<IMove> getPlayerMoves(){
		return GameInfo.getInstance().player.getPokemon().getMoves();
	}

	/*
	 * TODO: make a menu view or something, this is retarded
	 */
	private void selectMove(final int selected) {
		final int moveSize = getPlayerMoves().size();
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
		player.selectMove(selectedMove);

		super.update(gc, arg1, delta);
	}
	private float leftOffset = 20f;
	private int showCount = 30;
	private LineWriterView out = new LineWriterView();

	@Override
	public void render(final GameContainer gc, final StateBasedGame arg1, final Graphics g)
			throws SlickException {
		g.setColor(new Color(255, 255, 255, 255));
		g.setLineWidth(5f);
		out.clear();
		out.setLocation(new Vector2f(20f, 150f));

		if(battle.isOver() && player.hasWon()) { //player won, don't show enemy stuff
			out.write("You are victorious! Press enter to leave");
		} else {
			enemyView.render(gc, g);
		}

		if(battle.isOver() && !player.hasWon()) { //player lost, dont show player
			out.getLocation().y += 300;
			out.write("You lost! Press enter to leave");
			out.write("To heal your pokemon, visit the old lady at the beginning!");
		} else {

			final List<IMove> myMoves = getPlayerMoves();
			for(int i = 0; i < myMoves.size(); i++) {
				if(i == selectedMove)
					g.setColor(new Color(255, 255, 255, 255));
				else
					g.setColor(new Color(255, 255, 255, 128));
				g.drawString(myMoves.get(i).getName() , leftOffset + 260f, 490f + (i * 15));
			}
            g.setColor(new Color(255, 255, 255, 255));
			playerView.render(gc,g);

			g.drawRect(490f, 15f, 300, 500);

			out.setLocation(new Vector2f(500,20f));
			out.write("Battle log:");
			final List<Turn> log = battle.getBattleLog();
			final int startIndex = 0;
			if(log.size() > showCount){
				log.remove(0);
			}
			for(final Turn str : log){
				out.write(str.getChange().toString());
			}
		}
		out.render(gc, g);

		super.render(gc, arg1, g);
	}



}
