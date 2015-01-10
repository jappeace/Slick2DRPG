package org.bakkes.game.events.key;

import org.bakkes.game.scripting.ScriptManager;

public class ScriptReloadListener implements IKeyListener {

	public void KeyDown(int key, char c) {
		if(key == 59) { //F1, should probably define somewhere?
			ScriptManager.loadScripts();
		}
	}

	public void KeyUp(int key, char c) {
		// TODO Auto-generated method stub
		
	}

}
