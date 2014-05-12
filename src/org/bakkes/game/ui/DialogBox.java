package org.bakkes.game.ui;

import java.awt.Font;

import org.bakkes.game.Game;
import org.bakkes.game.events.GameKeyListener;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.UnicodeFont;

public class DialogBox implements DrawableGameComponent, GameKeyListener {
	
	private static UnicodeFont font;
	
	private String text;
	private Game container;
	public DialogBox(Game game) {
		this(game, "");
	}
	
	public DialogBox(Game g, String text) {
		this.text = text;
		container = g;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public void show() {
		container.addComponent(this);
		container.addKeyListener(this);
	}
	
	public void hide() {
		container.removeComponent(this);
		container.removeKeyListener(this);
	}

	public void Render(GameContainer gc, Graphics g) {
		g.setColor(Color.white);
		g.fillRect(1, 400, 800, 200);
		g.setLineWidth(5f);
		g.setColor(Color.black);
		g.drawRect(1, 400, 797, 197);
		g.resetLineWidth();
		//g.setFont(font);
		g.drawString(this.getText(), 20, 420);
	}

	public void KeyDown(int key, char c) {
		if(key == 57) {
			hide();
		}
	}

	public void KeyUp(int key, char c) {
		// TODO Auto-generated method stub
		
	}
	
	static {
		Font tempFont = new Font("Verdana", Font.BOLD, 20);
		font = new UnicodeFont(tempFont, tempFont.getSize(), tempFont.isBold(), tempFont.isItalic());
	}
}
