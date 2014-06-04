package org.bakkes.game;

/*
 * Is a singleton which holds the latest game info
 */
public class GameInfo {
	public static final int SCREEN_WIDTH = 800;
	public static final int SCREEN_HEIGHT = 600;
	
	private static GameInfo instance;
	
	public static GameInfo getInstance() {
		if(instance == null)
			instance = new GameInfo();
		return instance;
	}
	
	public int delta;
	
	public GameInfo() {
		
	}
}
