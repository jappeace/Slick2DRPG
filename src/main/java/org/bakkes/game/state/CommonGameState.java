package org.bakkes.game.state;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.bakkes.game.GameInfo;
import org.bakkes.game.events.IDialogClosed;
import org.bakkes.game.events.key.DebugToggleListener;
import org.bakkes.game.events.key.IKeyListener;
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
	protected Queue<DialogBox> dialogQueue = new LinkedList<>();
	private List<DrawableGameComponent> drawables;
	private List<IKeyListener> keyListeners;
	protected IDialogClosed dialogCallback;


	@Override
	public void init(final GameContainer gc, final StateBasedGame arg1)
			throws SlickException {
		drawables = new ArrayList<>();
		keyListeners = new ArrayList<>();
		this.keyListeners.add(new DebugToggleListener());
	}

	/**
	 * @param delta = tpf time that has passed between frames
	 */
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

	public void addKeyListener(final IKeyListener keylistener) {
		this.keyListeners.add(keylistener);
	}

	public void removeComponent(final DrawableGameComponent gameComponent) {
		this.drawables.remove(gameComponent);
	}

	public void removeKeyListener(final IKeyListener keylistener) {
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
		final ArrayList<IKeyListener> keyListenersCopy = new ArrayList<IKeyListener>(keyListeners); //Create a copy because scripts can register keypress components.
		System.out.println("Pressed key: " + key);
		final Iterator<IKeyListener> it = keyListenersCopy.iterator();
		while(it.hasNext()) {
			final IKeyListener next = it.next();
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
		final ArrayList<IKeyListener> keyListenersCopy = new ArrayList<IKeyListener>(keyListeners); //Create a copy because scripts can register keyrelease components.
		System.out.println("Released key: " + key);
		final Iterator<IKeyListener> it = keyListenersCopy.iterator();
		while(it.hasNext()) {
			final IKeyListener next = it.next();
			next.KeyUp(key, c);
		}
    }

	public void showDialog(final String text) {
		final DialogBox d = new DialogBox(this, text);
		d.show();
	}

	//give a callback which should be called when the dialog is closed
	public void showDialog(final String text, final IDialogClosed callback) {
		showDialog(text);
		dialogCallback = callback;
	}

	public void activateDialogs() {
		if(currentDialogBox == null && dialogQueue.size() > 0)
			checkDialogs();
	}
}
