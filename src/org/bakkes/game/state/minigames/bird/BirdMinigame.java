package org.bakkes.game.state.minigames.bird;

import org.bakkes.game.state.minigames.Minigame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class BirdMinigame extends Minigame {
	public static final int BIRD_MINIGAME_STATE_ID = 1;
	
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2)
			throws SlickException {

	}

	@Override
	public int getID() {
		return BIRD_MINIGAME_STATE_ID;
	}

}
