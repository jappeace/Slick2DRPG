package org.bakkes.game.controller.event.input;

import com.google.inject.Inject;
import org.bakkes.game.controller.event.MainMenuHandler;
import org.bakkes.game.controller.event.MenuShower;

public class MenuToggleListener extends AKeyListener{
	private @Inject MenuShower shower;
	private @Inject MainMenuHandler handler;

	@Override
	public void KeyDown(final Key key) {
		if(key.isMenu()) {
            shower.showHandler(handler);
		}
	}
}
