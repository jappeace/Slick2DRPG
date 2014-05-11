package org.bakkes.game.math;

import org.bakkes.game.Constants;

public class GridGraphicTranslator {

	public static Vector2 PixelsToGridPixels(Vector2 p) {
		return new Vector2((p.getX() - (p.getX() % Constants.TILE_WIDTH)),
				(p.getY() - (p.getY() % Constants.TILE_HEIGHT)));
	}
	
	public static Vector2 PixelsToGrid(Vector2 p) {
		return new Vector2((p.getX() - (p.getX() % Constants.TILE_WIDTH)) / Constants.TILE_WIDTH,
				(p.getY() - (p.getY() % Constants.TILE_HEIGHT)) / Constants.TILE_HEIGHT);
	}
	
	public static Vector2 GridToPixels(Vector2 p) {
		return new Vector2(p.getX() * Constants.TILE_WIDTH, p.getY() * Constants.TILE_HEIGHT);
	}
	
	public static boolean PixelsInTile(Vector2 pixels, Vector2 destTile) {
		Vector2 translated = GridToPixels(destTile);
		return (int)pixels.getX() == (int)translated.getX() && (int)pixels.getY() == (int)translated.getY();
	}
}
