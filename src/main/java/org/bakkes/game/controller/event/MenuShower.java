package org.bakkes.game.controller.event;

import org.bakkes.game.controller.MenuController;
import org.bakkes.game.controller.state.overworld.OverworldState;
import org.bakkes.game.view.components.Menu;

import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * reduces the boiler plate of showing a menu
 */
public class MenuShower {

	/*
	 * TODO: remove provider and make more sensible (overworld is a singleton)
	 * the provider breaks a dependecy cycle
	 */
	private @Inject Provider<OverworldState> game;
	private @Inject Provider<MenuController> menu;
	private @Inject Provider<Menu> menuProvider;
	public void showHandler(final IMenuHandler handler){
        final MenuController controller =menu.get();
        final Menu menu = menuProvider.get();
		menu.x(550);
		menu.y(20);
		menu.width(180);
		menu.height(300);
        controller.set(menu, handler);
        game.get().setKeyListener(controller);
	}
}
