package org.bakkes.game.events;

import org.bakkes.game.Game;
import org.bakkes.game.scripting.ScriptManager;
import org.bakkes.game.state.OverworldState;

public class TalkToNPCListener implements GameKeyListener {
	private OverworldState game;
	
	
	public TalkToNPCListener(OverworldState playingGameState) {
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
