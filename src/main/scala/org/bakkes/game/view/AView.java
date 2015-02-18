package org.bakkes.game.view;

import com.google.inject.Inject;
import org.bakkes.game.view.components.LineWriter;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public abstract class AView implements IRenderable{

	protected @Inject LineWriter out;
	@Override
	public void render(final Graphics g) {
		final Color previousColor = g.getColor();

		if(out != null){
            out.clear();
		}

		renderView(g);

		if(out != null){
            out.render(g);
		}

		g.resetLineWidth();
		g.setColor(previousColor);
	}

	protected abstract void renderView(Graphics g);
}
