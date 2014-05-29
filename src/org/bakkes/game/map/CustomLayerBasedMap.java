package org.bakkes.game.map;

import org.bakkes.game.math.Vector2;
import org.newdawn.slick.tiled.TiledMap;

public class CustomLayerBasedMap implements CustomTileBasedMap {
	
	private TiledMap map;
	private int[] blockingLayers;
	
	public CustomLayerBasedMap(TiledMap map, int... blockingLayers) {
		this.map = map;
		this.blockingLayers = blockingLayers;
	}
	
	public int getWidth() {
		return map.getWidth();
	}

	public int getHeight() {
		return map.getHeight();
	}

	public float getCost(Vector2 location) {
		return 1.0f;
	}

	public boolean isBlocked(Vector2 location) {
		for(int blockingLayer : blockingLayers) {
			if(isBlocked(location, blockingLayer))
				return true;
		}
		return false;
	}
	
    private boolean isBlocked(Vector2 position, int layer) {
    	return map.getTileId((int)position.getX(), (int)position.getY(), layer) != 0 || 
    			map.getTileId((int)position.getX(), (int)position.getY() + 1, layer) != 0 || 
        		map.getTileId((int)position.getX() + 1, (int)position.getY(), layer) != 0 || 
        		map.getTileId((int)position.getX() + 1, (int)position.getY() + 1, layer) != 0;//fix 2nd & 3rd statement, cheap fix because player is 32 px

    }

}
