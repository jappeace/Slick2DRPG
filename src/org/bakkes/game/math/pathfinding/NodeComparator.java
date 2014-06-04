package org.bakkes.game.math.pathfinding;

import java.util.Comparator;

public class NodeComparator implements Comparator<Node> {

	public int compare(Node arg0, Node arg1) {
		
		if(arg0.f > arg1.f)
			return 1;
		else if(arg0.f < arg1.f)
			return -1;
		return 0;
	}

}
