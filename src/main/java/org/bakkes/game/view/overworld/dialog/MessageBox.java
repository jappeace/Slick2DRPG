package org.bakkes.game.view.overworld.dialog;

import org.newdawn.slick.Graphics;

/**
 * ## Message box
 *
 * used to show a blocking message
 */
public class MessageBox extends AMessageBox implements IMessageBox {

	protected static final float margin = 10;
	protected final Box box = new Box(margin,400,800-margin*2,200-margin);

	@Override
	protected void renderView(final Graphics g) {
		box.render(g);
		out.setLocation(box.x() + margin, box.y() + margin);
		out.write(getTitle());
		out.write("");
		out.write(getText());
	}
}
