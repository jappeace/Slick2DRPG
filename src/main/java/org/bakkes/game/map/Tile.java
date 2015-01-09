package org.bakkes.game.map;

import org.newdawn.slick.geom.Vector2f;

public class Tile {
	public static final int WIDTH = 16;
	public static final int HEIGHT = 16;

	public static Vector2f toVector2f(){
		return new Vector2f(WIDTH,HEIGHT);
	}
}
