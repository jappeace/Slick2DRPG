package org.bakkes.game.view.overworld.dialog;

import org.bakkes.game.model.font.MutableFont;
import org.bakkes.game.view.components.Box;
import org.bakkes.game.view.components.TextLine;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;

import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * ## Message box
 *
 * used to show a blocking message
 */
public class MessageBox extends AMessageBox implements IMessageBox {

	protected static final float margin = 10;
	protected final Box box = new Box(margin,400,800-margin*2,200-margin);
	private @Inject Provider<TextLine> textLineProvider;
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
		out.setLocation(box.x() + margin, box.y() + margin);
		final TextLine t = textLineProvider.get();
		t.setFont(titleFont);
		out.write(getTitle());
		out.write("");
		out.write(getText());
	}
}
