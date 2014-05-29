package org.bakkes.game.math.pathfinding.distance;

import org.bakkes.game.math.Vector2;

public interface IDistanceCalculator {
	public int getDistance(Vector2 from, Vector2 goal);
}
