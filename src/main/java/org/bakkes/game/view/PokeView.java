package org.bakkes.game.view;

import java.util.List;

import org.bakkes.game.model.pokemon.IMove;
import org.bakkes.game.model.pokemon.IPokemonStatistics;
import org.bakkes.game.model.pokemon.Pokemon;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public class PokeView extends AView{

	private final LineWriterView out;
	private Image avatar;
	private final Pokemon pokemon;
	private final Vector2f topLeft;
	public boolean renderMoves = true;

	public PokeView(final Pokemon pokemon, final Vector2f topLeft){
		this.pokemon = pokemon;
		this.topLeft = topLeft;
		try {
			avatar = new Image(pokemon.getSpritePath());
		} catch (final SlickException e) {
		}
		out = new LineWriterView();
	}
	@Override
	public void render(final GameContainer gc, final Graphics g) {
		out.clear();
        g.drawImage(avatar, topLeft.x + 260, topLeft.y);
        if(renderMoves){
            out.setLocation(new Vector2f(topLeft.x + 260, topLeft.y + 75));
            out.write("Moves:");
            final List<IMove> moves = pokemon.getMoves();
            for(int i = 0; i < moves.size(); i++) {
                out.write(moves.get(i).getName());
            }
        }

        g.drawRect(topLeft.x - 5f, topLeft.y + 72, 200f, 85f);
        out.setLocation(new Vector2f(topLeft.x, topLeft.y + 75));
        out.write("Stats:");
        final IPokemonStatistics stat = pokemon.getCurrentStats();
        out.write("HP: " + stat.getHealth() + " SP: " + stat.getSpeed());
        out.write("DF: " + stat.getDefence() + " AT: " + stat.getAttack());
        out.render(gc, g);

	}

}
