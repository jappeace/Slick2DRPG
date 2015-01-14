package org.bakkes.game.view;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public abstract class AView implements IRenderable{

	@Override
	public void render(final Graphics g) {
		final Color previousColor = g.getColor();
		final float lineWidth = g.getLineWidth();
		renderView(g);
		g.setLineWidth(lineWidth);
		g.setColor(previousColor);
	}

	protected abstract void renderView(Graphics g);
}
