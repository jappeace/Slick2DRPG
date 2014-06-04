package org.bakkes.game.math.pathfinding.heuristic;

import org.bakkes.game.math.Vector2;

//4 directions of distance, so manhattan is probably best
public class ManhattanHeuristic implements IHeuristicCalculator {
	
	public int getDistance(Vector2 from, Vector2 goal) {
		return (int) (Math.abs(from.getX() - goal.getX()) + Math.abs(from.getY() - goal.getY()));
	}
	
}
