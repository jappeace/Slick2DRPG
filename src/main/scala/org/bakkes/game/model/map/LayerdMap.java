package org.bakkes.game.model.map;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.util.pathfinding.PathFindingContext;
import org.newdawn.slick.util.pathfinding.TileBasedMap;

import java.nio.file.Path;

@Singleton
public class LayerdMap implements TileBasedMap, IAreaNameAcces {

	private TiledMap map;
	private int[] blockingLayers;
	private int grassLayer;
	private String areaName;
	private @Inject BlockedTileTracker tracker;
	private @Inject @Named("map") Path path;

	/**
	 * load a new map, can't be done by injection because the opengl context needs to be created first
	 * @param url
	 */
	public void load(final String areaName){
		this.areaName = areaName;
		try {
			this.map = new TiledMap(path.resolve(areaName + ".tmx").toString());
		} catch (final SlickException e) {
			e.printStackTrace();
            return;
		}
		this.blockingLayers = new int[]{map.getLayerIndex("objects")};
		grassLayer = map.getLayerIndex("grass");
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
		for(final Tile tile : tracker.findAllBlockedTiles()){
			if(tile.equals(location)){
				return true;
			}
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
	/* (non-Javadoc)
	 * @see org.bakkes.game.model.map.IAreaNameAcces#getAreaName()
	 */
	@Override
	public String getAreaName() {
		return areaName;
	}
}
