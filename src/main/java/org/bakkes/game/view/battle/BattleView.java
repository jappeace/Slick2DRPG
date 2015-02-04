package org.bakkes.game.view.battle;

import org.bakkes.game.controller.state.battle.Battle;
import org.bakkes.game.controller.state.battle.contestent.PlayerContestent;
import org.bakkes.game.model.GameInfo;
import org.bakkes.game.model.battle.move.IMove;
import org.bakkes.game.view.AView;
import org.bakkes.game.view.SpriteType;
import org.bakkes.game.view.components.Menu;
import org.bakkes.game.view.components.TextLine;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.util.Log;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class BattleView extends AView{

	@Inject Battle battle;
	@Inject BattleLogView battleLog;
	private BattlePokeView enemyView;
	private BattlePokeView playerView;
	private Menu moves;
	private PlayerContestent playerContestent;
	@Inject
	public BattleView(final Provider<BattlePokeView> viewProvider, final Provider<TextLine> lines, final Menu moveMenu, final PlayerContestent playerContestent){
		enemyView = viewProvider.get();
		playerView = viewProvider.get();
		enemyView.setPokemon(playerContestent.getTargetPokemon(), SpriteType.front);
		playerView.setPokemon(playerContestent.getOwnPokemon(), SpriteType.back);
		playerView.y(GameInfo.SCREEN_HEIGHT-playerView.height());
		moves=moveMenu;
		this.playerContestent = playerContestent;
		Log.debug("moves: h" + playerContestent.getOwnPokemon().getMoves());
        for(final IMove move : playerContestent.getOwnPokemon().getMoves()){
        	moveMenu.add(lines.get().setText(move.getName()));
        }
	}
	@Override
	protected void renderView(final Graphics g) {
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
			moves.render(g);
			playerView.render(g);
			battleLog.render(g);
		}
		out.render(g);
	}

}
