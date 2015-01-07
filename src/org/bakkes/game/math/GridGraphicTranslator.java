package org.bakkes.game.math;

import org.bakkes.game.Constants;
import org.newdawn.slick.geom.Vector2f;

public class GridGraphicTranslator {

	public static Vector2f PixelsToGridPixels(final Vector2f p) {
		return new Vector2f((p.getX() - (p.getX() % Constants.TILE_WIDTH)),
				(p.getY() - (p.getY() % Constants.TILE_HEIGHT)));
	}

	public static Vector2f PixelsToGrid(final Vector2f p) {
		return new Vector2f((p.getX() - (p.getX() % Constants.TILE_WIDTH)) / Constants.TILE_WIDTH,
				(p.getY() - (p.getY() % Constants.TILE_HEIGHT)) / Constants.TILE_HEIGHT);
	}

	public static Vector2f GridToPixels(final Vector2f p) {
		return new Vector2f(p.getX() * Constants.TILE_WIDTH, p.getY() * Constants.TILE_HEIGHT);
	}

	public static boolean PixelsInTile(final Vector2f pixels, final Vector2f destTile) {
		final Vector2f translated = GridToPixels(destTile);
		return (int)pixels.getX() == (int)translated.getX() && (int)pixels.getY() == (int)translated.getY();
	}
}
