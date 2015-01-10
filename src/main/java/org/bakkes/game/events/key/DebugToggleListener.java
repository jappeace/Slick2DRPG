package org.bakkes.game.events.key;

import org.bakkes.game.GameInfo;
import org.newdawn.slick.Input;

public class DebugToggleListener implements IKeyListener {

	@Override
	public void KeyDown(int key, char c) {
		// TODO Auto-generated method stub
		if(key == Input.KEY_F2) {
			GameInfo.SHOW_DEBUG_INFO = !GameInfo.SHOW_DEBUG_INFO;
		}
	}

	@Override
	public void KeyUp(int key, char c) {
		// TODO Auto-generated method stub
		
	}

}
