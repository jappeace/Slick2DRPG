package org.bakkes.game.model.entity;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import nl.jappieklooster.annotation.Nullable;
import org.bakkes.game.model.map.IBlocksTiles;
import org.bakkes.game.model.map.Tile;

import java.util.Collection;

@Singleton
public class EntityTracker<T extends IBlocksTiles> {
    private @Inject Provider<Collection<T>> enteties;
	public @Nullable T findEntityByTile(final Tile tile) {
        for(final T entity: getEntities()){
        	if(entity.getBlockedTiles().contains(tile)){
        		return entity;
        	}
        }
		return null;
	}

	public Collection<T> getEntities(){
		return enteties.get();
	}
}
