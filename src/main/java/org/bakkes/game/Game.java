package org.bakkes.game;

import org.bakkes.game.state.BattleState;
import org.bakkes.game.state.PlayingGameState;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.KeyListener;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Game extends StateBasedGame implements KeyListener {
	public Game(final String title) {
		super(title);
	}

	@Override
	public void initStatesList(final GameContainer gc) throws SlickException {
		this.addState(new PlayingGameState());
		this.addState(new BattleState());
	}



}
