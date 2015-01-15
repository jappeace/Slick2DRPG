package org.bakkes.game.view;

import java.util.List;

import org.bakkes.game.model.battle.BattleLogEvent;
import org.bakkes.game.model.battle.Turn;
import org.bakkes.game.model.pokemon.IPokemonStatistics;
import org.bakkes.game.model.pokemon.Pokemon;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

import com.google.inject.Inject;

public class BattleLogView extends AView {

	private @Inject List<BattleLogEvent> log;
	private static final Vector2f LINE_WRITER_START = new Vector2f(10,5);
	private static final Shape SHAPE = new Rectangle(490,15,300,500);
	private static final int MAX_TURN_LINES = 5; // how many lines can it take to describe a turn
	@Override
	protected void renderView(final Graphics g) {

		g.setLineWidth(5f);
		g.setColor(Color.white);
        g.draw(SHAPE);

        out.setLocation(SHAPE.getLocation().add(LINE_WRITER_START));
        out.write("Battle log:");
        for(int i = log.size() - 1; i >= 0; i --){
        	out.write("Turn #"+i+":");
        	renderTurn(log.get(i));
        	out.write("------");
        	if(out.getHeight() + MAX_TURN_LINES * out.lineIncrease > SHAPE.getHeight()){
        		break;
        	}
        }
	}
	private void renderTurn(final BattleLogEvent event){
		final Turn current = event.getTurn();

		final IPokemonStatistics change = current.getChange();
		final Pokemon aggresor = current.getAgressor();
		final Pokemon victem = current.getTarget();
		if(event.isExtraTurn){
            out.write(aggresor.getName() + " got extra turn");
		}
        out.write(aggresor.getName() + " uses " + current.getMove().getName() );
        out.write(victem.getName() + " takes " + change.getHealth() + " damage");
	}

}
