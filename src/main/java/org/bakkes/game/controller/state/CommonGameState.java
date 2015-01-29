package org.bakkes.game.controller.state;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bakkes.game.controller.IUpdatable;
import org.bakkes.game.controller.input.CompositeKeyListener;
import org.bakkes.game.controller.input.IKeyListener;
import org.bakkes.game.controller.input.Key;
import org.bakkes.game.model.GameInfo;
import org.bakkes.game.view.IRenderable;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.sun.istack.internal.Nullable;

public abstract class CommonGameState extends BasicGameState {
	protected boolean inputEnabled = true;
	private IRenderable overlay = null;
	private List<IRenderable> drawables;

	private @Inject Collection<IUpdatable> controllers;
	private IKeyListener keyListener;
	private @Inject Provider<CompositeKeyListener> defaultKeysProvider;
	private @Inject Provider<Key> keyProvider;

	/**
	 *
	 * @param to if null, keylisteners will be reset
	 */
	public void setKeyListener(final @Nullable IKeyListener to){
		if(to == null){
			keyListener = defaultKeysProvider.get();
			return;
		}
		keyListener = to;
	}

	@Override
	public void init(final GameContainer gc, final StateBasedGame arg1)
			throws SlickException {
		drawables = new ArrayList<>();
	}

	/**
	 * @param delta = tpf time that has passed between frames
	 */
	@Override
	public void update(final GameContainer gc, final StateBasedGame arg1, final int delta)
			throws SlickException {
		for(final IUpdatable controller : controllers){
			controller.update(delta);
		}
	}

	@Override
	public void render(final GameContainer gc, final StateBasedGame arg1, final Graphics g)
			throws SlickException {

		for(final IRenderable drawable : drawables) {
			drawable.render(g);
		}
		g.drawString("Debug info: " + (GameInfo.SHOW_DEBUG_INFO ? "ON" : "OFF"), 10, 25);
		if(overlay != null)
			overlay.render(g);
	}

	public void addComponent(final IRenderable gameComponent) {
		this.drawables.add(gameComponent);
	}

	public void removeComponent(final IRenderable gameComponent) {
		this.drawables.remove(gameComponent);
	}

	public void setOverlay(final IRenderable overlay) {
		this.overlay = overlay;
	}

	/**
	 * @see org.newdawn.slick.InputListener#keyPressed(int, char)
	 */
	@Override
	public void keyPressed(final int key, final char c) {
		final Key k = keyProvider.get();
		k.setId(key);
		keyListener.KeyDown(k);
	}

	/**
	 * @see org.newdawn.slick.InputListener#keyReleased(int, char)
	 */
	@Override
	public void keyReleased(final int key, final char c) {
		final Key k = keyProvider.get();
		k.setId(key);
		keyListener.KeyUp(k);
	}
}
