package org.bakkes.game.model.map;

import org.bakkes.game.R;
import org.newdawn.slick.util.pathfinding.AStarPathFinder;
import org.newdawn.slick.util.pathfinding.PathFinder;
import org.newdawn.slick.util.pathfinding.TileBasedMap;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Names;

public class WorldModule extends AbstractModule{

	@Override
	protected void configure() {
		bind(String.class).annotatedWith(Names.named("path-script-npcs")).toInstance(R.npcScript);
		bind(TileBasedMap.class).to(LayerdMap.class);
		bind(PathFinder.class).to(AStarPathFinder.class);
	}

	@Provides AStarPathFinder providePathFinder(final TileBasedMap map){
		return new AStarPathFinder(map, 100, false);
	}

}
