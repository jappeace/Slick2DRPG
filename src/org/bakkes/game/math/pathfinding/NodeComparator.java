package org.bakkes.game.math.pathfinding;

import java.util.Comparator;

public class NodeComparator implements Comparator<Node> {

	public int compare(Node arg0, Node arg1) {
		float diff0 = arg0.h + arg0.cost;
		float diff1 = arg1.h + arg1.cost;
		
		if(diff0 > diff1)
			return 1;
		else if(diff0 < diff1)
			return -1;
		return 0;
	}

}
