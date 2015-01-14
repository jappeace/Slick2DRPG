package org.bakkes.game;

import java.util.Collection;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.KeyListener;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class Game extends StateBasedGame implements KeyListener {
	@Inject private Collection<GameState> states;
	@Inject
	public Game(final @Named("game-title") String title) {
		super(title);
	}

	@Override
	public void initStatesList(final GameContainer gc) throws SlickException {
		for(final GameState state : states){
			this.addState(state);
		}
	}



}
