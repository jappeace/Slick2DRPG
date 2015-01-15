package org.bakkes.game.view.overworld;

import org.bakkes.game.controller.events.key.IKeyListener;
import org.bakkes.game.controller.state.CommonGameState;
import org.bakkes.game.view.AView;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.util.Log;

import com.google.common.base.Preconditions;
import com.google.inject.Inject;

public class DialogBox extends AView implements IKeyListener {

	private String title = "";
	private String text = "";

	public final String getTitle() {
		return title;
	}
	public final String getText() {
		return text;
	}
	public final void setTitle(final String title) {
		this.title = Preconditions.checkNotNull(title, "");
	}
	private CommonGameState container;
	@Inject
	public DialogBox(final CommonGameState commonGameState) {
		container = commonGameState;
	}

	public void setText(final String text) {
		this.text = Preconditions.checkNotNull(text, "");
	}

	public void show() {
		Log.info("showing dialogbox with: " + text);
		container.queueDialogBox(this);
	}

	@Override
	public void KeyDown(final int key, final char c) {
	}

	@Override
	public void KeyUp(final int key, final char c) {

	}

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
		out.write(title);
		out.write(text);
	}
}
