package org.bakkes.game.map;

import org.bakkes.game.math.Vector2;

public interface CustomTileBasedMap {
	int getWidth();
	int getHeight();
	float getCost(Vector2 location);
	boolean isBlocked(Vector2 location);
}
