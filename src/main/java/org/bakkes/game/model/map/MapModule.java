package org.bakkes.game.model.map;

import org.newdawn.slick.util.pathfinding.AStarPathFinder;
import org.newdawn.slick.util.pathfinding.PathFinder;
import org.newdawn.slick.util.pathfinding.TileBasedMap;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

public class MapModule extends AbstractModule{

	@Override
	protected void configure() {
		bind(TileBasedMap.class).to(LayerdMap.class);
		bind(IAreaNameAcces.class).to(LayerdMap.class);
		bind(PathFinder.class).to(AStarPathFinder.class);
	}

	@Provides AStarPathFinder providePathFinder(final TileBasedMap map){
		return new AStarPathFinder(map, 100, false);
	}

}
