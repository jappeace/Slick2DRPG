package org.bakkes.game.controller.event.input;

import org.bakkes.game.controller.MenuController;
import org.bakkes.game.controller.event.IItemSelectHandler;
import org.bakkes.game.controller.state.overworld.OverworldState;

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
			final MenuController controller =menu.get();
			controller.add(new String[]{"pokemon", "items"});
			controller.setItemSelectHandler(new IItemSelectHandler(){
				@Override
				public void select(final int item) {

				}
			});
			game.get().setKeyListener(controller);
		}
	}
}
