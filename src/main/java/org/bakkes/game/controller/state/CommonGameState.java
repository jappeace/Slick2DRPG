package org.bakkes.game.controller.state;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.bakkes.game.controller.events.key.DebugToggleListener;
import org.bakkes.game.controller.events.key.IKeyListener;
import org.bakkes.game.model.GameInfo;
import org.bakkes.game.view.IRenderable;
import org.bakkes.game.view.overworld.dialog.IMessageBox;
import org.bakkes.game.view.overworld.dialog.MessageBoxState;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public abstract class CommonGameState extends BasicGameState {
	protected boolean inputEnabled = true;
	protected IMessageBox currentDialogBox = null;
	protected Queue<IMessageBox> dialogQueue = new LinkedList<>();
	private List<IRenderable> drawables;


	@Override
	public void init(final GameContainer gc, final StateBasedGame arg1)
			throws SlickException {
		drawables = new ArrayList<>();
		this.getKeyListeners().add(new DebugToggleListener());
	}

	public boolean add(final IKeyListener listener){
		return getKeyListeners().add(listener);
	}
	public boolean remove(final IKeyListener listener){
		return getKeyListeners().remove(listener);
	}
	protected abstract List<IKeyListener> getKeyListeners();
	/**
	 * @param delta = tpf time that has passed between frames
	 */
	@Override
	public void update(final GameContainer gc, final StateBasedGame arg1, final int delta)
			throws SlickException {
		if(dialogQueue.isEmpty()){
			return;
		}
		if(currentDialogBox == null){
			nextDialog();
		}
	}

	@Override
	public void render(final GameContainer gc, final StateBasedGame arg1, final Graphics g)
			throws SlickException {

		for(final IRenderable drawable : drawables) {
			drawable.render(g);
		}
		g.drawString("Debug info: " + (GameInfo.SHOW_DEBUG_INFO ? "ON" : "OFF"), 10, 25);
		if(currentDialogBox != null)
			currentDialogBox.render(g);
	}

	public void addComponent(final IRenderable gameComponent) {
		this.drawables.add(gameComponent);
	}

	public void removeComponent(final IRenderable gameComponent) {
		this.drawables.remove(gameComponent);
	}

	public void queueDialogBox(final IMessageBox dialogBox) {
		dialogBox.setState(MessageBoxState.Queued);
		dialogQueue.add(dialogBox);
	}

	public void nextDialog() {
		if(currentDialogBox != null){
			currentDialogBox.setState(MessageBoxState.Done);
		}
		if(dialogQueue.size() > 0) {
			inputEnabled = false;
			currentDialogBox = dialogQueue.remove();
			currentDialogBox.setState(MessageBoxState.Showing);
			return;
		}
        inputEnabled = true;
        currentDialogBox = null;
	}

	@Override
    public void keyPressed(final int key, final char c) {
		if(!inputEnabled)
			return;
		for(final IKeyListener listener : getKeyListeners()){
			listener.KeyDown(key, c);
		}
    }

    @Override
    public void keyReleased(final int key, final char c) {
		if(key == Keyboard.KEY_SPACE) { //space
			nextDialog();
		}
		if(!inputEnabled)
			return;
		for(final IKeyListener listener : getKeyListeners()){
			listener.KeyUp(key, c);
		}
    }

	public void activateDialogs() {
		if(currentDialogBox == null && dialogQueue.size() > 0)
			nextDialog();
	}

}
