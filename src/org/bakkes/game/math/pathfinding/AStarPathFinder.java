package org.bakkes.game.math.pathfinding;

import java.util.ArrayList;
import java.util.PriorityQueue;

import org.bakkes.game.map.LayerBasedMap;
import org.bakkes.game.math.Vector2;
import org.bakkes.game.math.pathfinding.distance.IDistanceCalculator;
import org.bakkes.game.math.pathfinding.distance.ManhattanDistance;
import org.newdawn.slick.util.pathfinding.Path;

public class AStarPathFinder implements IPathFinder {

	private LayerBasedMap map;
	private IDistanceCalculator distanceCalculator;
	
	public AStarPathFinder(LayerBasedMap map) {
		this(map, new ManhattanDistance());
	}
	
	public AStarPathFinder(LayerBasedMap map, IDistanceCalculator distanceCalculator) {
		this.map = map;
		this.distanceCalculator = distanceCalculator;
	}
	
	public Path getShortestPath(Vector2 startPosition, Vector2 endPosition) {
		PriorityQueue<Node> open = new PriorityQueue<Node>(new NodeComparator());
		ArrayList<Node> closed = new ArrayList<Node>();
		open.add(new Node(startPosition, distanceCalculator.getDistance(startPosition, endPosition)));
		
		while(open.size() > 0) {
			Node smallest = open.remove();
			closed.add(smallest);
			
		}
		return null;
	}

}
