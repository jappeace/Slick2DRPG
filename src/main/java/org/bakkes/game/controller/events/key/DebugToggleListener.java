package org.bakkes.game.controller.events.key;

import org.bakkes.game.model.GameInfo;
import org.newdawn.slick.Input;

public class DebugToggleListener implements IKeyListener {

	@Override
	public void KeyDown(final int key, final char c) {
		// TODO Auto-generated method stub
		if(key == Input.KEY_F2) {
			GameInfo.SHOW_DEBUG_INFO = !GameInfo.SHOW_DEBUG_INFO;
		}
	}

	@Override
	public void KeyUp(final int key, final char c) {
		// TODO Auto-generated method stub

	}

}
