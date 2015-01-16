package org.bakkes.game.model;



/**
 * holds a bunch of static data I didn't know where to put or I realy should write a class for
 */
public class GameInfo {
	public static final int SCREEN_WIDTH = 800;
	public static final int SCREEN_HEIGHT = 600;
	public static boolean SHOW_DEBUG_INFO = true; // WTF
	// TODO: make a class for handeling the xp bullshit
	public static final int XP_MODIFIER = 5;

	// TODO: make a system for this, for example per area load a script with pokemon in it ranging form level to level
	public static final String[] WILD = new String[]{"caterpie", "charmender"};
	public static final int WILD_POKE_LEVEL = 10;
}
