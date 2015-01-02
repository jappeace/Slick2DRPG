package org.bakkes.game.math.pathfinding;

import org.bakkes.game.math.Vector2;

public class Node implements Comparable<Node>{
	private Vector2 position;

	public Node parent;
	public float h;
	public float cost;
	public float f;

	public Node(final Vector2 position) {
		super();
		this.position = position;
	}

	public Vector2 getPosition() {
		return position;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(final Node parent) {
		this.parent = parent;
	}

	@Override
	public int compareTo(final Node o) {
		if(this.f > o.f)
			return 1;
		else if(this.f < o.f)
			return -1;
		return 0;
	}
}
