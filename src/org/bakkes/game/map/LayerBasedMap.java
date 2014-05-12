package org.bakkes.game.map;

import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.util.pathfinding.PathFindingContext;
import org.newdawn.slick.util.pathfinding.TileBasedMap;

public class LayerBasedMap implements TileBasedMap {

	private TiledMap map;
	private int blockingLayerId;
	private int npcLayerId;
	
    public LayerBasedMap(TiledMap map, int blockingLayerId, int npcLayerId) {
        this.map = map;
        this.blockingLayerId = blockingLayerId;
        this.npcLayerId = npcLayerId;
    }

    public boolean blocked(PathFindingContext ctx, int x, int y) {	
    	return blocked(x, y, blockingLayerId) || blocked(x, y, npcLayerId);
    }

    public float getCost(PathFindingContext ctx, int x, int y) {
        return 1.0f;
    }

    public int getHeightInTiles() {
        return map.getHeight();
    }

    public int getWidthInTiles() {
        return map.getWidth();
    }

    public void pathFinderVisited(int arg0, int arg1) {}

    private boolean blocked(int x, int y, int layer) {
    	return map.getTileId(x, y, layer) != 0 || map.getTileId(x, y + 1, layer) != 0 
        		|| map.getTileId(x + 1, y, layer) != 0 || map.getTileId(x + 1, y + 1, layer) != 0;//fix 2nd & 3rd statement, cheap fix because player is 32 px

    }

}
