package org.bakkes.game.view;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import com.google.inject.Inject;

public abstract class AView implements IRenderable{

	protected @Inject LineWriterView out;
	@Override
	public void render(final Graphics g) {
		final Color previousColor = g.getColor();
		final float lineWidth = g.getLineWidth();

		if(out != null){
            out.clear();
		}

		renderView(g);

		if(out != null){
            out.render(g);
		}

		g.setLineWidth(lineWidth);
		g.setColor(previousColor);
	}

	protected abstract void renderView(Graphics g);
}
