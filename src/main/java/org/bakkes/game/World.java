package org.bakkes.game;

import java.util.LinkedList;
import java.util.List;

import org.bakkes.game.entity.Person;
import org.bakkes.game.map.LayerdMap;
import org.bakkes.game.scripting.ScriptLoader;
import org.bakkes.game.scripting.dsl.ADsl;
import org.bakkes.game.scripting.dsl.NpcDsl;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.util.pathfinding.AStarPathFinder;
import org.newdawn.slick.util.pathfinding.PathFinder;

public class World {

	private LayerdMap layerMap;
	private final AStarPathFinder pathFinder;
	private final List<Person> people = new LinkedList<>();

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
		final ADsl dsl = new NpcDsl(getPeople());
		new ScriptLoader().load(R.npcScript, dsl);
		layerMap = new LayerdMap(map, map.getLayerIndex("objects"), map.getLayerIndex("npc"));
        pathFinder = new AStarPathFinder(layerMap, 100, false);

        for(final Person person : getPeople()){

        	person.setLocation(layerMap.getNPCLocation(person.getId()));
        }
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
	public List<Person> getPeople() {
		return people;
	}
}
