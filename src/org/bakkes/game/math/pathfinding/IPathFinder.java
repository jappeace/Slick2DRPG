package org.bakkes.game.math.pathfinding;

import org.bakkes.game.math.Vector2;
import org.newdawn.slick.util.pathfinding.Path;

public interface IPathFinder {
	public Path getShortestPath(Vector2 startPosition, Vector2 endPosition);
}
