package org.bakkes.game.controller.input;

import org.lwjgl.input.Keyboard;

/**
 * puts a layer around the static keyboard class and what the keys means
 * later on it allows the keys to become flexible
 */
public class Key {
	/**
	 * DO NOT try to read this directly,
	 * create a method if you want to check for a specific action (up down left right, conformation etc)..
	 * doing this allows the input to be configurable later
	 */
	private int id;

	public void setId(final int id) {
		this.id = id;
	}

	public boolean isConfirm(){
		return id == Keyboard.KEY_SPACE || id == Keyboard.KEY_RETURN;
	}

	public boolean isDebug(){
		return id == Keyboard.KEY_F2;
	}
	public boolean isMenu(){
		return id == Keyboard.KEY_ESCAPE;
	}
	public boolean isUp(){
		return id == Keyboard.KEY_UP || id == Keyboard.KEY_W;
	}
	public boolean isDown(){
		return id == Keyboard.KEY_DOWN || id == Keyboard.KEY_S;
	}
	public boolean isLeft(){
		return id == Keyboard.KEY_LEFT || id == Keyboard.KEY_A;
	}
	public boolean isRight(){
		return id == Keyboard.KEY_RIGHT || id == Keyboard.KEY_D;
	}
}
