package org.bakkes.game.model.map;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import com.google.inject.Singleton;

/**
 * keeps track of blocked tiles that change dynamicly.
 * does not keep track of the staticly blocked tiles defined in the .tmx files
 */
@Singleton
public class BlockedTileTracker {
	Map<String, Collection< ? extends IBlocksTiles>> blockers = new HashMap<>();

	public void putBlockedTiles(final String layerName, final Collection<? extends IBlocksTiles> tiles){
		blockers.put(layerName, tiles);
	}

	Collection<Tile> getAllBlockedTiles(){
		final Collection<Tile> result = new LinkedList<>();
		for(final Collection<? extends IBlocksTiles> tileset : blockers.values()){
			for(final IBlocksTiles blocker : tileset){
                result.addAll(blocker.getBlockedTiles());
			}
		}
		return result;
	}
}
