package org.bakkes.game.view;

import java.awt.Font;

import org.bakkes.game.controller.events.key.IKeyListener;
import org.bakkes.game.controller.state.CommonGameState;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.UnicodeFont;

public class DialogBox implements IRenderable, IKeyListener {

	private static UnicodeFont font;

	private String text;
	private CommonGameState container;
	public DialogBox(final DialogBox source){
		this.text = source.text;
		this.container = container;
	}
	public DialogBox(final CommonGameState game) {
		this(game, "");
	}

	public DialogBox(final CommonGameState commonGameState, final String text) {
		this.text = text;
		container = commonGameState;
	}

	public void setText(final String text) {
		this.text = text;
	}

	public void show() {
		container.queueDialogBox(new DialogBox(this));
	}

	@Override
	public void render(final GameContainer gc, final Graphics g) {
		g.setColor(Color.white);
		g.fillRect(1, 400, 800, 200);
		g.setLineWidth(5f);
		g.setColor(Color.black);
		g.drawRect(1, 400, 797, 197);
		g.setLineWidth(1f);
		g.resetLineWidth();
		g.drawString(text, 20, 420);
	}

	@Override
	public void KeyDown(final int key, final char c) {
	}

	@Override
	public void KeyUp(final int key, final char c) {

	}

	static {
		final Font tempFont = new Font("Verdana", Font.BOLD, 20);
		font = new UnicodeFont(tempFont, tempFont.getSize(), tempFont.isBold(), tempFont.isItalic());
	}
}
