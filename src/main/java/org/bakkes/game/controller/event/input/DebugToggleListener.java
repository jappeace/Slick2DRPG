package org.bakkes.game.controller.event.input;

import org.bakkes.game.model.GameInfo;

public class DebugToggleListener extends AKeyListener implements IKeyListener {

	@Override
	public void KeyDown(final Key key) {
		if(key.isDebug()) {
			GameInfo.SHOW_DEBUG_INFO = !GameInfo.SHOW_DEBUG_INFO;
		}

	}
}
