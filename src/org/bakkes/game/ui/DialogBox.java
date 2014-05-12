package org.bakkes.game.ui;

import java.awt.event.KeyEvent;

import org.bakkes.game.Game;
import org.bakkes.game.events.GameKeyListener;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class DialogBox implements DrawableGameComponent, GameKeyListener {

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
		
	}

	public void KeyDown(int key, char c) {
		if(key == KeyEvent.VK_SPACE) {
			hide();
		}
	}

	public void KeyUp(int key, char c) {
		// TODO Auto-generated method stub
		
	}
}
