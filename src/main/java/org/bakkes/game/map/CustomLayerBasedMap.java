package org.bakkes.game.map;

import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.util.pathfinding.PathFindingContext;
import org.newdawn.slick.util.pathfinding.TileBasedMap;

public class CustomLayerBasedMap implements TileBasedMap {

	private TiledMap map;
	private int[] blockingLayers;
	private int grassLayer;
	public CustomLayerBasedMap(final TiledMap map, final int... blockingLayers) {
		this.map = map;
		this.blockingLayers = blockingLayers;
		this.grassLayer = map.getLayerIndex("grass");
	}

	public boolean isGrass(final Tile location) {
		return map.getTileId(location.left, location.top, grassLayer) != 0;
	}

    private boolean isBlocked(final Tile tile, final int layer) {
    	return map.getTileId(tile.left, tile.top, layer) != 0 ||
    			map.getTileId(tile.left, tile.top + 1, layer) != 0 ||
        		map.getTileId(tile.left + 1, tile.top, layer) != 0 ||
        		map.getTileId(tile.left + 1, tile.top + 1, layer) != 0;//fix 2nd & 3rd statement, cheap fix because player is 32 px

    }

	@Override
	public int getWidthInTiles() {
		return map.getWidth();
	}

	@Override
	public int getHeightInTiles() {
		return map.getHeight();
	}

	@Override
	public void pathFinderVisited(final int x, final int y) {
		// TODO Auto-generated method stub

	}
	public boolean isBlocked(final Tile location){
		for(final int blockingLayer : blockingLayers) {
			if(isBlocked(location, blockingLayer))
				return true;
		}
		return false;
	}

	@Override
	public boolean blocked(final PathFindingContext context, final int tx, final int ty) {
		return isBlocked(new Tile(tx,ty));
	}

	@Override
	public float getCost(final PathFindingContext context, final int tx, final int ty) {
		return 1.0f;
	}

}
