package org.bakkes.game.controller.scripting.dsl;

import groovy.lang.Closure;

import java.util.HashMap;
import java.util.Map;

import org.bakkes.game.model.entity.npc.Person;
import org.bakkes.game.model.map.Direction;
import org.bakkes.game.model.map.Tile;

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

	private Person target;

	public void setTarget(final Person target) {
		this.target = target;
	}

	public void onInteract(final Closure callback){
		target.setInteract(callback);
	}

	public void facing(final String direction){
		isFacingSet = true;
		target.setFacing(directions.get(direction));
	}

	@Override
	public boolean isDone(){
		return super.isDone() && isPositionSet && isFacingSet;
	}
	/**
	 * set to a tile location
	 * @param x
	 * @param y
	 */
	public void location(final Integer left, final Integer top){
		isPositionSet = true;
		target.setPosition(new Tile(left, top).topLeftPixels());
	}
}
