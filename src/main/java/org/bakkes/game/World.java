package org.bakkes.game;

import org.bakkes.game.map.LayerdMap;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.util.pathfinding.AStarPathFinder;
import org.newdawn.slick.util.pathfinding.PathFinder;

public class World {

	private LayerdMap layerMap;
	private final AStarPathFinder pathFinder;

    public static PathFinder getPathFinder(){
    	return getWorld().pathFinder;
    }
	private World() {
		TiledMap map = null;
		try {
			map = new TiledMap(R.map + "map.tmx");
		} catch (final SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		layerMap = new LayerdMap(map, map.getLayerIndex("objects"), map.getLayerIndex("npc"));
        pathFinder = new AStarPathFinder(layerMap, 100, false);
	}

	public LayerdMap getLayerMap() {
		return layerMap;
	}

	private static World instance;

	public static World getWorld() {
		if(instance == null)
			instance = new World();
		return instance;
	}
}
