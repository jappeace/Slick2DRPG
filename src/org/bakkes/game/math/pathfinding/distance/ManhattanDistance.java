package org.bakkes.game.math.pathfinding.distance;

import org.bakkes.game.math.Vector2;

public class ManhattanDistance implements IDistanceCalculator {
	
	public int getDistance(Vector2 from, Vector2 goal) {
		return (int) (Math.abs(goal.getX() - from.getX()) + Math.abs(goal.getY() - from.getY()));
	}
	
}
