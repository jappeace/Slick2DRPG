package org.bakkes.game.controller.state.overworld.event.key;

import com.google.inject.Inject;
import org.bakkes.game.controller.state.event.input.AKeyListener;
import org.bakkes.game.controller.state.event.input.Key;
import org.bakkes.game.controller.state.overworld.event.MainMenuHandler;
import org.bakkes.game.controller.state.overworld.event.MenuShower;

public class MenuToggleListener extends AKeyListener {
	private @Inject MenuShower shower;
	private @Inject MainMenuHandler handler;

	@Override
	public void KeyDown(final Key key) {
		if(key.isMenu()) {
            shower.showHandler(handler);
		}
	}
}
