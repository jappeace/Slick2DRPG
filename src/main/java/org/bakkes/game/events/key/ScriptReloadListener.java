package org.bakkes.game.events.key;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.util.Log;


public class ScriptReloadListener implements IKeyListener {

	@Override
	public void KeyDown(final int key, final char c) {
		if(key == Keyboard.KEY_F1) {
			Log.warn("Put here the script loader");
		}
	}

	@Override
	public void KeyUp(final int key, final char c) {
		// TODO Auto-generated method stub

	}

}
