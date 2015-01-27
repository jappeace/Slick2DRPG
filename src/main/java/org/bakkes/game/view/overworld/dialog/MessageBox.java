package org.bakkes.game.view.overworld.dialog;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

/**
 * ## Message box
 *
 * used to show a blocking message
 */
public class MessageBox extends ADialog implements IDialog {


	@Override
	protected void renderView(final Graphics g) {
		g.setColor(Color.white);
		g.fillRect(1, 400, 800, 200);
		g.setLineWidth(5f);
		g.setColor(Color.black);
		g.drawRect(1, 400, 797, 197);
		g.setLineWidth(1f);
		g.resetLineWidth();
		out.setLocation(new Vector2f(20,420));
		out.write(getTitle());
		out.write(getText());
	}
}
