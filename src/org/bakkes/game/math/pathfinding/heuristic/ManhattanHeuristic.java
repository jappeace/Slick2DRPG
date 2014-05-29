package org.bakkes.game.math.pathfinding.heuristic;

import org.bakkes.game.math.Vector2;

public class ManhattanHeuristic implements IHeuristicCalculator {
	
	public int getDistance(Vector2 from, Vector2 goal) {
		return (int) (Math.abs(goal.getX() - from.getX()) + Math.abs(goal.getY() - from.getY()));
	}
	
}
