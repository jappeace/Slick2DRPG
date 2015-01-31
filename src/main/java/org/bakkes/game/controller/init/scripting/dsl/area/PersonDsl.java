package org.bakkes.game.controller.init.scripting.dsl.area;

import java.util.HashMap;
import java.util.Map;

import org.bakkes.game.controller.init.scripting.dsl.AOverworldEntityDsl;
import org.bakkes.game.controller.init.scripting.dsl.anotation.Required;
import org.bakkes.game.controller.init.scripting.dsl.anotation.Result;
import org.bakkes.game.model.entity.npc.Person;
import org.bakkes.game.model.map.Direction;

public class PersonDsl extends AOverworldEntityDsl{
	boolean isFacingSet = false;
	private static Map<String, Direction> directions = new HashMap<>();
	static{
		// lazy scripting people don't even want to import directions
		directions.put("south", Direction.South);
		directions.put("north", Direction.North);
		directions.put("east", Direction.East);
		directions.put("west", Direction.West);
	}

	private Person target = new Person();

	@Override
	@Result
	public Person getTarget(){
		return target;
	}

	@Required
	public void facing(final String direction){
		isFacingSet = true;
		target.setFacing(directions.get(direction));
	}

	@Override
	public boolean isDone(){
		return super.isDone() && isFacingSet;
	}

}
