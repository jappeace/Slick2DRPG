package org.bakkes.game.controller.event.input;

import org.bakkes.game.controller.event.MainMenuHandler;
import org.bakkes.game.controller.event.MenuShower;

import com.google.inject.Inject;

public class MenuToggleListener extends AKeyListener{
	/*
	 * TODO: remove provider and make more sensible (overworld is a singleton)
	 */
	private final MenuShower shower;

	@Inject
	public MenuToggleListener(final MenuShower shower, final MainMenuHandler handler){
		shower.setMenuHandler(handler);
		this.shower = shower;
	}
	@Override
	public void KeyDown(final Key key) {
		if(key.isMenu()) {
			shower.show();
		}
	}
}
