package org.bakkes.game.state;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import org.bakkes.game.GameInfo;
import org.bakkes.game.events.DebugToggleListener;
import org.bakkes.game.events.DialogClosed;
import org.bakkes.game.events.GameKeyListener;
import org.bakkes.game.ui.DialogBox;
import org.bakkes.game.ui.DrawableGameComponent;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
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


	@Override
	public void init(final GameContainer gc, final StateBasedGame arg1)
			throws SlickException {
		drawables = new ArrayList<DrawableGameComponent>();
		keyListeners = new ArrayList<GameKeyListener>();
		this.keyListeners.add(new DebugToggleListener());
	}

	@Override
	public void update(final GameContainer gc, final StateBasedGame arg1, final int delta)
			throws SlickException {
		GameInfo.getInstance().delta = delta;
	}

	@Override
	public void render(final GameContainer gc, final StateBasedGame arg1, final Graphics g)
			throws SlickException {

		for(final DrawableGameComponent drawable : drawables) {
			drawable.Render(gc, g);
		}
		g.drawString("Debug info: " + (GameInfo.SHOW_DEBUG_INFO ? "ON" : "OFF"), 10, 25);
		if(currentDialogBox != null)
			currentDialogBox.Render(gc, g);
	}

	public void addComponent(final DrawableGameComponent gameComponent) {
		this.drawables.add(gameComponent);
	}

	public void addKeyListener(final GameKeyListener keylistener) {
		this.keyListeners.add(keylistener);
	}

	public void removeComponent(final DrawableGameComponent gameComponent) {
		this.drawables.remove(gameComponent);
	}

	public void removeKeyListener(final GameKeyListener keylistener) {
		this.keyListeners.remove(keylistener);
	}

	public void queueDialogBox(final DialogBox dialogBox) {
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
    public void keyPressed(final int key, final char c) {
		if(!inputEnabled)
			return;
		final ArrayList<GameKeyListener> keyListenersCopy = new ArrayList<GameKeyListener>(keyListeners); //Create a copy because scripts can register keypress components.
		System.out.println("Pressed key: " + key);
		final Iterator<GameKeyListener> it = keyListenersCopy.iterator();
		while(it.hasNext()) {
			final GameKeyListener next = it.next();
			next.KeyDown(key, c);
		}
    }

    @Override
    public void keyReleased(final int key, final char c) {
		if(key == 57) { //space
			checkDialogs();
		}
		if(!inputEnabled)
			return;
		final ArrayList<GameKeyListener> keyListenersCopy = new ArrayList<GameKeyListener>(keyListeners); //Create a copy because scripts can register keyrelease components.
		System.out.println("Released key: " + key);
		final Iterator<GameKeyListener> it = keyListenersCopy.iterator();
		while(it.hasNext()) {
			final GameKeyListener next = it.next();
			next.KeyUp(key, c);
		}
    }

	public void showDialog(final String text) {
		final DialogBox d = new DialogBox(this, text);
		d.show();
	}

	//give a callback which should be called when the dialog is closed
	public void showDialog(final String text, final DialogClosed callback) {
		showDialog(text);
		dialogCallback = callback;
	}

	public void activateDialogs() {
		if(currentDialogBox == null && dialogQueue.size() > 0)
			checkDialogs();
	}
}
