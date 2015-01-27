package org.bakkes.game.view.overworld.dialog;

import org.newdawn.slick.Graphics;

public class Dialog extends MessageBox{

	private final Box anwserBox = new Box(
			box.x() + box.width()*0.8f,
			box.y(),
            box.width()*0.2f,
            box.height()*0.3f
			);
	@Override
	protected void renderView(final Graphics g) {
		super.renderView(g);
		anwserBox.render(g);
		out.setLocation(anwserBox.x() + margin, anwserBox.y() + margin);
		out.write("yes");
		out.write("no");
	}
}
