package org.bakkes.game.controller.input;

import org.bakkes.game.controller.MenuController;
import org.bakkes.game.controller.state.OverworldState;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class MenuToggleListener extends AKeyListener{
	/*
	 * TODO: remove provider and make more sensible (overworld is a singleton)
	 */
	private @Inject Provider<OverworldState> game;
	private @Inject Provider<MenuController> menu;

	@Override
	public void KeyDown(final Key key) {
		if(key.isMenu()) {
			game.get().setKeyListener(menu.get());
		}
	}
}
