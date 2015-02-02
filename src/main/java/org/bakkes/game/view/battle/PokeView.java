package org.bakkes.game.view.battle;

import org.bakkes.game.R;
import org.bakkes.game.model.ImageCache;
import org.bakkes.game.model.pokemon.IPokemonStatistics;
import org.bakkes.game.model.pokemon.Pokemon;
import org.bakkes.game.view.AView;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.sun.xml.internal.ws.util.StringUtils;

public class PokeView extends AView{

	private Image avatar;
	private final Pokemon pokemon;
	private final Vector2f topLeft;
	public boolean renderMoves = true;

	@Inject
	public PokeView(final Pokemon pokemon, final @Named("position") Vector2f topLeft, final ImageCache imageCache){
		this.pokemon = pokemon;
		this.topLeft = topLeft;
		try {
			avatar = imageCache.load(R.pokemonSprites + pokemon.getSpriteName() + "/front");
		} catch (final SlickException e) {
		}
	}
	@Override
	public void renderView(final Graphics g) {
        g.drawImage(avatar, topLeft.x + 260, topLeft.y);

        g.drawRect(topLeft.x - 5f, topLeft.y + 37, 200f, 130f);
        out.setLocation(new Vector2f(topLeft.x, topLeft.y + 40));
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
        g.drawLine(topLeft.x + 4, topLeft.y + yOffset, topLeft.x + width, topLeft.y + yOffset);
        g.setColor(old);
        g.setLineWidth(lineSize);
	}

}
