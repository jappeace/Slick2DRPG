package org.bakkes.game.view.overworld.dialog;

import com.google.inject.Inject;
import org.bakkes.game.model.font.MutableFont;
import org.bakkes.game.view.components.Box;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;

/**
 * ## Message box
 *
 * used to show a blocking message
 */
public class MessageBox extends AMessageBox implements IMessageBox {

	protected static final float margin = 10;
	protected final Box box = new Box(margin,400,800-margin*2,200-margin);
	private Font titleFont;
	@Inject
	public MessageBox(final MutableFont font){
		super();
		font.setDecoration(java.awt.Font.BOLD);
		titleFont = font;
	}

	@Override
	protected void renderView(final Graphics g) {
		box.render(g);
		out.x(box.x() + margin);
		out.y(box.y() + margin);
		out.write(getTitle(), titleFont);
		out.write("");
		out.write(getText());
	}
}
