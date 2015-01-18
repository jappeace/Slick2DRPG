package org.bakkes.game.controller.scripting.dsl;

import groovy.lang.Closure;

import java.util.HashMap;
import java.util.Map;

import org.bakkes.game.controller.scripting.dsl.anotation.Required;
import org.bakkes.game.controller.scripting.dsl.anotation.Result;
import org.bakkes.game.model.entity.npc.Person;
import org.bakkes.game.model.map.Direction;
import org.bakkes.game.model.map.Tile;
import org.newdawn.slick.util.Log;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class PersonDsl extends ADsl{
	boolean isFacingSet = false;
	boolean isPositionSet = false;
	private static Map<String, Integer> directions = new HashMap<>();
	static{
		// lazy scripting people don't even want to import directions
		directions.put("south", Direction.SOUTH);
		directions.put("north", Direction.NORTH);
		directions.put("east", Direction.EAST);
		directions.put("west", Direction.WEST);
	}

	private Person target = new Person();
	private @Inject Provider<InteractDsl> dslProvider;

	@Result
	public Person getTarget(){
		return target;
	}
	public void onInteract(final Closure callback){
		final InteractDsl dsl = dslProvider.get();
		dsl.target = target;
		callback.setDelegate(dsl);
		target.setInteract(callback);
	}

	@Required
	public void facing(final String direction){
		isFacingSet = true;
		target.setFacing(directions.get(direction));
	}

	@Override
	public boolean isDone(){
		return super.isDone() && isPositionSet && isFacingSet;
	}
	@Required
	public void location(final Integer left, final Integer top){
		isPositionSet = true;
		target.setPosition(new Tile(left, top).topLeftPixels());
	}

	public void name(final String name){
		Log.info("setting name to " + name);
		target.setName(name);
	}
}
