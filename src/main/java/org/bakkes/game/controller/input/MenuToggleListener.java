package org.bakkes.game.controller.input;

import org.bakkes.game.controller.IItemSelectHandler;
import org.bakkes.game.controller.MenuController;
import org.bakkes.game.controller.state.OverworldState;
import org.newdawn.slick.util.Log;

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
					Log.info("bluh" + item);
				}
			});
			game.get().setKeyListener(controller);
		}
	}
}
