package org.bakkes.game.view.overworld.dialog;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

/**
 * ## Message box
 *
 * used to show a blocking message
 */
public class MessageBox extends ADialog implements IDialog {

	private static final float margin = 10;
	private static final float lineWidth = 3;
	private static final Shape shape = new Rectangle(margin,400,800-margin*2,200-margin);

	@Override
	protected void renderView(final Graphics g) {
		g.setColor(Color.white);
		g.fill(shape);
		g.setLineWidth(5f);
		g.setColor(Color.black);
		g.setLineWidth(lineWidth);
		g.drawRect(shape.getX(), shape.getY(), shape.getWidth()-lineWidth/2, shape.getHeight()-lineWidth/2);
		out.setLocation(new Vector2f(shape.getX() + margin,shape.getY() + margin));
		out.write(getTitle());
		out.write("");
		out.write(getText());
	}
}
