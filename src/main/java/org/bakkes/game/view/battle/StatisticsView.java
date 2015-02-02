package org.bakkes.game.view.battle;

import org.bakkes.game.model.pokemon.IPokemonStatistics;
import org.bakkes.game.model.pokemon.Pokemon;
import org.bakkes.game.view.components.AShape;
import org.bakkes.game.view.components.Box;
import org.bakkes.game.view.components.LineWriter;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

import com.google.inject.Inject;
import com.sun.xml.internal.ws.util.StringUtils;

public class StatisticsView extends AShape{

	private @Inject LineWriter out;
	private Box box = new Box(0,0,200,130);
	private Pokemon pokemon;

	public void setPokemon(final Pokemon to){
		pokemon = to;
	}
	@Override
	public float width() {
		return box.width();
	}

	@Override
	public float height() {
		return box.height();
	}
    @Override
	protected void onPositionChange(final Vector2f position){
    	box.x(position.x);
    	box.y(position.y);
    	out.x(x());
    	out.y(y()+40);
    }

	@Override
	public void render(final Graphics g) {
        out.write(StringUtils.capitalize(pokemon.getName()) + " level: " + pokemon.getLevel());
        out.write("Stats:");
        final IPokemonStatistics stat = pokemon.getCurrentStats();
        out.write("HP: " + stat.getHealth() + " SP: " + stat.getSpeed());
        out.write("DF: " + stat.getDefence() + " AT: " + stat.getAttack());
        out.write("Health: ");
        out.write("");
        out.write("XP: ");

        final IPokemonStatistics normalStat = pokemon.getNormalStats();
        drawBar(g, 125, (float)stat.getHealth() / (float) normalStat.getHealth(), new Color(200,0,56));
        drawBar(g, 155, (float)pokemon.getExperiance()/(float)pokemon.calculateXpFor(pokemon.getLevel()+1), new Color(9,214,84));
	}
	/**
	 *
	 * @param g
	 * @param fraction how much of the bar needs to be vissible between 0 .. 1
	 * @param color
	 */
	private void drawBar(final Graphics g, final float yOffset, final float fraction, final Color color){
		if(fraction > 1){
			return;
		}
		if(fraction < 0){
			return;
		}
        final float width = 187f * fraction;
        final Color old = g.getColor();
        final float lineSize = g.getLineWidth();
        g.setColor(color);
        g.setLineWidth(12f);
        g.drawLine(x() + 4, y() + yOffset, x() + width, y() + yOffset);
        g.setColor(old);
        g.setLineWidth(lineSize);
	}
}
