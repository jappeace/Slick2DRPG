package org.bakkes.game.math.pathfinding;

import org.bakkes.game.math.Vector2;

public class Node {
	private Vector2 position;
	
	public Node parent;
	public float h;
	public float cost;
	
	public Node(Vector2 position) {
		super();
		this.position = position;
	}

	public Vector2 getPosition() {
		return position;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}
}
