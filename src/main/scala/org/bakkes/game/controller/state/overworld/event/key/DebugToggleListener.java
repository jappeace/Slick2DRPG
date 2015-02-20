package org.bakkes.game.controller.state.overworld.event.key;

import org.bakkes.game.controller.state.event.input.AKeyListener;
import org.bakkes.game.controller.state.event.input.IKeyListener;
import org.bakkes.game.controller.state.event.input.Key;
import org.bakkes.game.model.GameInfo;

public class DebugToggleListener extends AKeyListener implements IKeyListener {

	@Override
	public void KeyDown(final Key key) {
		if(key.isDebug()) {
			GameInfo.SHOW_DEBUG_INFO = !GameInfo.SHOW_DEBUG_INFO;
		}

	}
}
