package org.bakkes.game.events;

import org.bakkes.game.Game;
import org.bakkes.game.PlayingGameState;
import org.bakkes.game.scripting.ScriptManager;

public class TalkToNPCListener implements GameKeyListener {
	private PlayingGameState game;
	
	
	public TalkToNPCListener(PlayingGameState playingGameState) {
		this.game = playingGameState;
	}
	
	public void KeyDown(int key, char c) {
		if(key == 57) { //space
			int facingNpc = game.getPlayer().getFacingNPC();
			System.out.println("Facing: " + facingNpc);
			if(facingNpc != -1)
				ScriptManager.executeFunction("talkto_" + facingNpc, game);
		}
	}

	public void KeyUp(int key, char c) {
		// TODO Auto-generated method stub

	}

}
