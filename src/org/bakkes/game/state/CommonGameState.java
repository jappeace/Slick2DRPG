package org.bakkes.game.state;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import org.bakkes.game.GameInfo;
import org.bakkes.game.events.DialogClosed;
import org.bakkes.game.events.GameKeyListener;
import org.bakkes.game.math.GridGraphicTranslator;
import org.bakkes.game.math.Vector2;
import org.bakkes.game.ui.DialogBox;
import org.bakkes.game.ui.DrawableGameComponent;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public abstract class CommonGameState extends BasicGameState {
	protected boolean inputEnabled = true;
	protected DialogBox currentDialogBox = null;
	protected Queue<DialogBox> dialogQueue = new LinkedList<DialogBox>();
	protected ArrayList<DrawableGameComponent> drawables;
	protected ArrayList<GameKeyListener> keyListeners;
	protected DialogClosed dialogCallback;

	
	public void init(GameContainer gc, StateBasedGame arg1)
			throws SlickException {
		drawables = new ArrayList<DrawableGameComponent>();
		keyListeners = new ArrayList<GameKeyListener>();
	}
	
	public void update(GameContainer gc, StateBasedGame arg1, int delta)
			throws SlickException {
		GameInfo.getInstance().delta = delta;
	}
	
	public void render(GameContainer gc, StateBasedGame arg1, Graphics g)
			throws SlickException {
		
		for(DrawableGameComponent drawable : drawables) {
			drawable.Render(gc, g);
		}
		if(currentDialogBox != null)
			currentDialogBox.Render(gc, g);
	}
	
	public void addComponent(DrawableGameComponent gameComponent) {
		this.drawables.add(gameComponent);
	}
	
	public void addKeyListener(GameKeyListener keylistener) {
		this.keyListeners.add(keylistener);
	}
	
	public void removeComponent(DrawableGameComponent gameComponent) {
		this.drawables.remove(gameComponent);
	}
	
	public void removeKeyListener(GameKeyListener keylistener) {
		this.keyListeners.remove(keylistener);
	}

	public void queueDialogBox(DialogBox dialogBox) {
		dialogQueue.add(dialogBox);
	}
	
	public void checkDialogs() {
		if(dialogQueue.size() > 0) {
			inputEnabled = false;
			currentDialogBox = dialogQueue.remove();
		} else {
			inputEnabled = true;
			currentDialogBox = null;
			if(dialogCallback != null) {
				dialogCallback.onClose();
			}
		}
	}
	
	@Override
    public void keyPressed(int key, char c) {
		if(!inputEnabled)
			return;
		ArrayList<GameKeyListener> keyListenersCopy = new ArrayList<GameKeyListener>(keyListeners); //Create a copy because scripts can register keypress components.
		System.out.println("Pressed key: " + key);
		Iterator<GameKeyListener> it = keyListenersCopy.iterator();
		while(it.hasNext()) {
			GameKeyListener next = it.next();
			next.KeyDown(key, c);
		}
    }

    @Override
    public void keyReleased(int key, char c) {
		if(key == 57) { //space
			checkDialogs();
		}
		if(!inputEnabled)
			return;
		ArrayList<GameKeyListener> keyListenersCopy = new ArrayList<GameKeyListener>(keyListeners); //Create a copy because scripts can register keyrelease components.
		System.out.println("Released key: " + key);
		Iterator<GameKeyListener> it = keyListenersCopy.iterator();
		while(it.hasNext()) {
			GameKeyListener next = it.next();
			next.KeyUp(key, c);
		}
    }
	
	public void showDialog(String text) {
		DialogBox d = new DialogBox(this, text);
		d.show();
	}
	
	//give a callback which should be called when the dialog is closed
	public void showDialog(String text, DialogClosed callback) {
		showDialog(text);
		dialogCallback = callback;
	}
	
	public void activateDialogs() {
		if(currentDialogBox == null && dialogQueue.size() > 0)
			checkDialogs();
	}
}
