package org.bakkes.game.math;

import org.bakkes.game.Constants;

public class GridGraphicTranslator {

	public static Point PixelsToGridPixels(Point p) {
		return new Point((p.getX() - (p.getX() % Constants.TILE_WIDTH)),
				(p.getY() - (p.getY() % Constants.TILE_HEIGHT)));
	}
	
	public static Point PixelsToGrid(Point p) {
		return new Point((p.getX() - (p.getX() % Constants.TILE_WIDTH)) / Constants.TILE_WIDTH,
				(p.getY() - (p.getY() % Constants.TILE_HEIGHT)) / Constants.TILE_HEIGHT);
	}
	
	public static Point GridToPixels(Point p) {
		return new Point(p.getX() * Constants.TILE_WIDTH, p.getY() * Constants.TILE_HEIGHT);
	}
	
	public static boolean PixelsInTile(Point pixels, Point destTile) {
		Point translated = GridToPixels(destTile);
		return (int)pixels.getX() == (int)translated.getX() && (int)pixels.getY() == (int)translated.getY();
	}
}
