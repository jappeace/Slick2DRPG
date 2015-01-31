package org.bakkes.game.controller.event;

import org.bakkes.game.controller.MenuController;
import org.bakkes.game.controller.state.overworld.OverworldState;

import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * reduces the boiler plate of showing a menu
 */
public class MenuShower {

	private @Inject Provider<OverworldState> game;
	private @Inject Provider<MenuController> menu;
	private IMenuHandler handler;
	public void setMenuHandler(final IMenuHandler handler){
		this.handler = handler;
	}

	public void show(){
        final MenuController controller =menu.get();
        controller.setItemSelectHandler(handler);
        game.get().setKeyListener(controller);
	}
}
