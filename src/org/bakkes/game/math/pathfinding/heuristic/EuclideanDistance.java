package org.bakkes.game.math.pathfinding.heuristic;

import org.bakkes.game.math.Vector2;

//not used ingame because our movement angles are fixed
public class EuclideanDistance implements IHeuristicCalculator {
	
	public int getDistance(Vector2 from, Vector2 goal) {
		float dx = Math.abs(from.getX() - goal.getX());
		float dy = Math.abs(from.getY() - goal.getY());
		return (int) (Math.sqrt(dx * dx + dy * dy));
	}
	
}
