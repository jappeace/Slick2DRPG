package org.bakkes.game.math.pathfinding.heuristic;

import org.bakkes.game.math.Vector2;

public interface IHeuristicCalculator {
	public int getDistance(Vector2 from, Vector2 goal);
}
