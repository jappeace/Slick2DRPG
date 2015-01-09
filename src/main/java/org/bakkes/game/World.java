package org.bakkes.game;

import org.bakkes.game.map.CustomLayerBasedMap;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.util.pathfinding.AStarPathFinder;
import org.newdawn.slick.util.pathfinding.PathFinder;

public class World {

	private TiledMap map;
	private CustomLayerBasedMap layerMap;
	private final AStarPathFinder pathFinder;

    public static PathFinder getPathFinder(){
    	return getWorld().pathFinder;
    }
	private World() {
		try {
			map = new TiledMap(R.map + "map.tmx");
		} catch (final SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		layerMap = new CustomLayerBasedMap(map, map.getLayerIndex("objects"), map.getLayerIndex("npc"));
        pathFinder = new AStarPathFinder(layerMap, 100, false);
	}

	public TiledMap getMap() {
		return map;
	}

	public CustomLayerBasedMap getLayerMap() {
		return layerMap;
	}

	private static World instance;

	public static World getWorld() {
		if(instance == null)
			instance = new World();
		return instance;
	}
}
