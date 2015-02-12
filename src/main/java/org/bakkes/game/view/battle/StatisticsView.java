package org.bakkes.game.view.battle;

import org.bakkes.game.model.pokemon.IPokemonStatistics;
import org.bakkes.game.model.pokemon.Pokemon;
import org.bakkes.game.view.components.AShape;
import org.bakkes.game.view.components.Box;
import org.bakkes.game.view.components.LineWriter;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

import com.google.inject.Inject;
import com.sun.xml.internal.ws.util.StringUtils;

public class StatisticsView extends AShape{

	private @Inject LineWriter out;
	private Box box = new Box(0,0,200,160);
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
	public void onChangePosition(final Vector2f position){
    	box.x(position.x);
    	box.y(position.y);
    }

    private static final float margin = 5;
	@Override
	public void render(final Graphics g) {
		box.render(g);
		out.clear();
    	out.x(x() + margin);
    	out.y(y()+ margin);
        out.write(StringUtils.capitalize(pokemon.getName()) + " level: " + pokemon.getLevel());
        out.write("Stats:");
        final IPokemonStatistics stat = pokemon.getCurrentStats();
        out.write("HP: " + stat.getHealth() + " SP: " + stat.getSpeed());
        out.write("DF: " + stat.getDefence() + " AT: " + stat.getAttack());
        out.write("Health: ");
        final IPokemonStatistics normalStat = pokemon.getNormalStats();
        out.write("");
        drawBar(g, (float)stat.getHealth() / (float) normalStat.getHealth(), new Color(200,0,56));
        out.write("XP: ");
        out.write("");
        drawBar(g, (float)pokemon.getExperiance()/(float)pokemon.calculateXpFor(pokemon.getLevel()+1), new Color(9,214,84));
        out.render(g);
	}
	/**
	 *
	 * @param g
	 * @param fraction how much of the bar needs to be vissible between 0 .. 1
	 * @param color
	 */
	private void drawBar(final Graphics g,final float fraction, final Color color){
        final float yOffset = out.getHeight() + margin;
		if(fraction > 1){
			return;
		}
		if(fraction < 0){
			return;
		}
        final float width = box.width();
        final float offset = margin+box.getBorderWidth();
        Rectangle rect = new Rectangle(x() + offset, y() + yOffset, width - offset * 2, 15);
        g.setColor(box.getBorderColor());
        final float borderWidth = 4;
        g.setLineWidth(borderWidth);
        g.draw(rect);
        rect = new Rectangle(x() + offset+borderWidth/2, y() + yOffset+borderWidth/2, fraction*width - offset * 2-borderWidth, 15-borderWidth);
        g.setColor(color);
        g.fill(rect);
	}
}
