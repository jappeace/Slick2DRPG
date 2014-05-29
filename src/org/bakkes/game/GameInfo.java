package org.bakkes.game;

/*
 * Is a singleton which holds the latest game info
 */
public class GameInfo {
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
