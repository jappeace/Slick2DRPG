package org.bakkes.game.math.pathfinding;

import java.util.ArrayList;
import java.util.PriorityQueue;

import org.bakkes.game.map.CustomLayerBasedMap;
import org.bakkes.game.map.CustomTileBasedMap;
import org.bakkes.game.math.Vector2;
import org.bakkes.game.math.pathfinding.heuristic.IHeuristicCalculator;
import org.bakkes.game.math.pathfinding.heuristic.ManhattanHeuristic;
import org.newdawn.slick.util.pathfinding.Path;

public class AStarPathFinder implements IPathFinder {

	private CustomTileBasedMap map;
	private IHeuristicCalculator distanceCalculator;
	private Node[][] nodes;
	
	/*
	 * Used for showing which path it took and which nodes were considered
	 */
	public ArrayList<Node> considered = new ArrayList<Node>();
	public ArrayList<Node> thePath = new ArrayList<Node>();
	
	public AStarPathFinder(CustomTileBasedMap map) {
		this(map, new ManhattanHeuristic());
	}
	
	public AStarPathFinder(CustomTileBasedMap map, IHeuristicCalculator distanceCalculator) {
		this.map = map;
		this.distanceCalculator = distanceCalculator;
		
		nodes = new Node[map.getWidth()][map.getHeight()];
		for(int i = 0; i < map.getWidth(); i++) {
			for(int j = 0; j < map.getHeight(); j++) {
				nodes[i][j] = new Node(new Vector2(i, j));
			}
		}
	}
	
	public Path getShortestPath(Vector2 startPosition, Vector2 endPosition) {	
		if(map.isBlocked(endPosition))
			return null;
		
		PriorityQueue<Node> open = new PriorityQueue<Node>();
		ArrayList<Node> closed = new ArrayList<Node>();
		open.add(new Node(startPosition));
		
		while(open.size() > 0) {
			Node current = open.remove();
			
			if(current.getPosition().getXI() == endPosition.getXI() && current.getPosition().getYI() == endPosition.getYI()) //reached the final position
				break;
			
			closed.add(current);
			considered.add(current);
			Node[] neighbors = getNeighbors(current);
			for(Node neighbor : neighbors) {
				if(neighbor == null) //node doesn't exist
					continue;
				if(closed.contains(neighbor))
					continue;
				
				float newCost = current.cost + 1f; //movement for every step is 1f
				if(!open.contains(neighbor)) {
					neighbor.parent = current;
					neighbor.cost = newCost;
					neighbor.h = distanceCalculator.getDistance(neighbor.getPosition(), endPosition);
					neighbor.f = neighbor.h + neighbor.cost;
					open.add(neighbor);
				} else if(newCost < neighbor.cost) {
					neighbor.parent = current;
					neighbor.cost = newCost;
					neighbor.f = neighbor.h + neighbor.cost;
				}
			}
		}
		//generate path by starting at endpoint and adding the parent until the startposition is reached.
		Path p = new Path();
		Node target = nodes[endPosition.getXI()][endPosition.getYI()];
		while(target != nodes[startPosition.getXI()][startPosition.getYI()]) {
			thePath.add(target);
			p.prependStep(target.getPosition().getXI(), target.getPosition().getYI());
			target = target.parent;
			
			if(target == null)
				break;
		}
		return p;
	}
	
	private Node[] getNeighbors(Node n) {
		return new Node[] {
				nodeIfInBounds(n.getPosition().getXI() + 1, n.getPosition().getYI()),
				nodeIfInBounds(n.getPosition().getXI(), n.getPosition().getYI() + 1),
				nodeIfInBounds(n.getPosition().getXI() - 1, n.getPosition().getYI()),
				nodeIfInBounds(n.getPosition().getXI(),n.getPosition().getYI() - 1)
		};
	}
	
	private Node nodeIfInBounds(int x, int y) {
		if(x > 0 && y > 0 && x < map.getWidth() && y < map.getHeight() && !map.isBlocked(new Vector2(x, y)))
			return nodes[x][y];
		return null;
	}
	

}
