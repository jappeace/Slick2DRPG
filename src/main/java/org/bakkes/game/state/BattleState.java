package org.bakkes.game.state;

import java.util.LinkedList;
import java.util.List;

import org.bakkes.game.GameInfo;
import org.bakkes.game.battle.BattleMaster;
import org.bakkes.game.model.battle.move.IMove;
import org.bakkes.game.model.entity.Player;
import org.bakkes.game.model.pokemon.IPokemonStatistics;
import org.bakkes.game.model.pokemon.Pokemon;
import org.bakkes.game.view.LineWriterView;
import org.bakkes.game.view.PokeView;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.Log;

public class BattleState extends CommonGameState {
	public static final int BATTLE_STATE_ID = 2;

	private BattleMaster battle = new BattleMaster();
	private PokeView enemyView;
	private PokeView playerView;
	private int selectedMove = 0;
	private boolean firstRun = true;
	private static final int XP_MODIFIER = 50;
	@Override
	public int getID() {
		return BATTLE_STATE_ID;
	}

	public void startWild() {
		final Player player = GameInfo.getInstance().player;
		//battle.setContestent(0, );

		this.enemyView = new PokeView(enemy, new Vector2f(20f,10f));
		this.playerView = new PokeView(player.getPokemon(), new Vector2f(20f, 400));
		this.playerView.renderMoves = false;
		firstRun = true;
	}

	private void selectMove(final int selected) {
		//final List<IMove> moves = battle.getPlayer().getMoves();
		if(selected >= moves.size())
			selectedMove = 0;
		else if(selected < 0)
			selectedMove = moves.size() - 1;
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
		final Pokemon player = battle.getPlayer();
		final Pokemon enemy = battle.getEnemy();
        battle.executeMove(player.getMoves().get(selectedMove), true);

        if(battle.isOver()){ // we won?
        	Log.info("we won!!");

			final IPokemonStatistics stats = player.addExperiance((int)(enemy.getLevel() * XP_MODIFIER / enemy.getSpecies().getTrainingSpeed()));
			if(stats != null){
				Log.info("level up: " + stats);
			}
            return;
        }

        //now execute opponents move
        final List<IMove> enemyMoves = battle.getEnemy().getMoves();
        battle.executeMove(enemyMoves.get(GameInfo.RANDOM.nextInt(enemyMoves.size())), false);

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

		if(battle.isOver() && battle.hasPlayerWon()) { //player won, don't show enemy stuff
			out.write("You are victorious! Press enter to leave");
		} else {
			enemyView.render(gc, g);
		}

		if(battle.isOver() && !battle.hasPlayerWon()) { //player lost, dont show player
			out.getLocation().y += 300;
			out.write("You lost! Press enter to leave");
			out.write("To heal your pokemon, visit the old lady at the beginning!");
		} else {

			final List<IMove> myMoves = battle.getPlayer().getMoves();
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
			final LinkedList<String> log = battle.getBattleLog();
			final int startIndex = 0;
			if(log.size() > showCount){
				log.removeFirst();
			}
			for(final String str : log){
				out.write(str);
			}
		}
		out.render(gc, g);

		super.render(gc, arg1, g);
	}



}
