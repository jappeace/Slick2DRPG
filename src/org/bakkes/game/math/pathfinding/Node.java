package org.bakkes.game.math.pathfinding;

import org.bakkes.game.math.Vector2;

public class Node {
	private Vector2 position;
	private int fScore;
	
	public Node(Vector2 position, int fScore) {
		super();
		this.position = position;
		this.fScore = fScore;
	}

	public Vector2 getPosition() {
		return position;
	}

	public int getfScore() {
		return fScore;
	}
	
	
}
