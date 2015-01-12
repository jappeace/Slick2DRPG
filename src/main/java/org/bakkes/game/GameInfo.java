package org.bakkes.game;

import java.util.Random;

import org.bakkes.game.model.entity.Player;
import org.newdawn.slick.state.StateBasedGame;

/*
 * Is a singleton which holds the latest game info
 */
public class GameInfo {
	public static final int SCREEN_WIDTH = 800;
	public static final int SCREEN_HEIGHT = 600;
	public static boolean SHOW_DEBUG_INFO = true;

	/**
	 * making this a constant creates a better 'random' because more shufeling happens
	 */
	public static final Random RANDOM = new Random();

	private static GameInfo instance;

	public static GameInfo getInstance() {
		if(instance == null)
			instance = new GameInfo();
		return instance;
	}

	public StateBasedGame stateGame;
	public Player player;
	public int delta;

	private GameInfo() {

	}
}
