package org.bakkes.game.map;

import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.util.pathfinding.PathFindingContext;
import org.newdawn.slick.util.pathfinding.TileBasedMap;

public class LayerdMap implements TileBasedMap {

	private final TiledMap map;
	private final int[] blockingLayers;
	private final int grassLayer;
	private final int npcLayer;

	public LayerdMap(final TiledMap map, final int... blockingLayers) {
		this.map = map;
		this.blockingLayers = blockingLayers;
		grassLayer = map.getLayerIndex("grass");
		npcLayer = map.getLayerIndex("npc");
	}

	public boolean isGrass(final Tile location) {
		return getTileId(location, grassLayer) != 0;
	}

    private boolean isBlocked(final Tile tile, final int layer) {
        //fix 2nd & 3rd statement, cheap fix because player is 32 px
    	return
            getTileId(tile.plus(new Tile(0,0)), layer) != 0 ||
            getTileId(tile.plus(new Tile(0,1)), layer) != 0 ||
            getTileId(tile.plus(new Tile(1,0)), layer) != 0 ||
            getTileId(tile.plus(new Tile(1,1)), layer) != 0;

    }
    private int getTileId(final Tile tile, final int layer){
    	return map.getTileId(tile.left, tile.top, layer);
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
	public void pathFinderVisited(final int x, final int y) {}
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

	public void render(final int i, final int j, final int tileIndexX, final int tileIndexY, final int k,
			final int l) {
		map.render(i,j,tileIndexX,tileIndexY,k,l);
	}
	public int getNPCidOn(final Tile tile){
		return Integer.parseInt(
            map.getTileProperty(
                    getTileId(tile, npcLayer), 
                    "npcid", 
                    "-1"
                )
            );
	}

}
