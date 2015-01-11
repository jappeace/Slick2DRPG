package org.bakkes.game;

import java.util.LinkedList;
import java.util.List;

import org.bakkes.game.model.entity.Person;
import org.bakkes.game.model.map.LayerdMap;
import org.bakkes.game.scripting.ScriptLoader;
import org.bakkes.game.scripting.dsl.ADsl;
import org.bakkes.game.scripting.dsl.NpcDsl;
import org.bakkes.game.state.CommonGameState;
import org.bakkes.game.view.DialogBox;
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
	private World(final String mapPath, final String scriptPath, final CommonGameState game) {
		TiledMap map = null;
		try {
			map = new TiledMap(mapPath);
		} catch (final SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		final ADsl dsl = new NpcDsl(people);
		new ScriptLoader().load(scriptPath, dsl);
		layerMap = new LayerdMap(map, map.getLayerIndex("objects"), map.getLayerIndex("npc"));
        pathFinder = new AStarPathFinder(layerMap, 100, false);

        for(final Person person : people){
        	person.setDialog(new DialogBox(game));
        }
	}

	public LayerdMap getLayerMap() {
		return layerMap;
	}

	private static World instance;

	public static World getWorld() {
		return instance;
	}
	public static World construct(final CommonGameState game){
		instance = new World(R.map + "map.tmx", R.npcScript, game);
		return instance;
	}

	public Person findPersonById(final int npc){
        if(npc != -1){
            for(final Person person : people){
                if(person.getId() == npc){
                    return person;
                }
            }
        }
        return null;
	}
}
