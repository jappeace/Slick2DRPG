package org.bakkes.game.scripting.dsl;

import groovy.lang.Closure;

import java.util.HashMap;
import java.util.Map;

import org.bakkes.game.entity.Person;
import org.bakkes.game.map.Direction;

public class PersonDsl extends ADsl{
	boolean isIdSet = false;
	boolean isFacingSet = false;
	private static Map<String, Integer> directions = new HashMap<>();
	static{
		// lazy scripting people don't even want to import directions
		directions.put("south", Direction.SOUTH);
		directions.put("north", Direction.NORTH);
		directions.put("east", Direction.EAST);
		directions.put("west", Direction.WEST);
	}

	private Person target;

	public void setTarget(final Person target) {
		this.target = target;
	}

	public void id(final int id){
		target.setId(id);
	}

	public void onInteract(final Closure callback){
		target.setInteract(callback);
	}

	public void facing(final String direction){
		target.setFacing(directions.get(direction));
	}

	@Override
	public boolean isDone(){
		return super.isDone() && isIdSet && isFacingSet;
	}
}
