package org.bakkes.game.events;

import org.bakkes.game.state.minigames.bird.BirdMinigame;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class ChangeToBirdListener implements GameKeyListener {
	private StateBasedGame game;
	
	public ChangeToBirdListener(StateBasedGame playingGameState) {
		this.game = playingGameState;
	}
	
	public void KeyDown(int key, char c) {
		if(key == 2) //"1"
			game.enterState(BirdMinigame.BIRD_MINIGAME_STATE_ID, new FadeOutTransition(), new FadeInTransition());
	}

	public void KeyUp(int key, char c) {

	}

}
