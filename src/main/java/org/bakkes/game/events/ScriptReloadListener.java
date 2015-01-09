package org.bakkes.game.events;

import org.bakkes.game.scripting.ScriptManager;

public class ScriptReloadListener implements GameKeyListener {

	public void KeyDown(int key, char c) {
		if(key == 59) { //F1, should probably define somewhere?
			ScriptManager.loadScripts();
		}
	}

	public void KeyUp(int key, char c) {
		// TODO Auto-generated method stub
		
	}

}
