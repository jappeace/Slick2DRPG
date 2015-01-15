package org.bakkes.game.model.entity.npc;

import java.util.LinkedList;
import java.util.List;

import org.bakkes.game.R;
import org.bakkes.game.controller.scripting.ScriptLoader;
import org.bakkes.game.controller.scripting.dsl.NpcDsl;
import org.bakkes.game.model.map.BlockedTileTracker;
import org.bakkes.game.model.map.IAreaNameAcces;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

public class PeopleModule extends AbstractModule{

	@Override
	protected void configure() {
	}

	private final List<Person> people = new LinkedList<>();
	private String previousAreaName = "";
	@Provides List<Person> providePeople(
        final NpcDsl npcdsl,
        final ScriptLoader scriptloader,
        final IAreaNameAcces areaNameHolder,
        final BlockedTileTracker tracker
    ){
		if(previousAreaName.equals(areaNameHolder.getAreaName())){
			return people;
		}
		previousAreaName = areaNameHolder.getAreaName();
		people.clear();
		npcdsl.setPeople(people);
		scriptloader.load(R.scripts + "/areas/" + areaNameHolder.getAreaName() + "/npc.dsl", npcdsl);

        tracker.putBlockedTiles("npc layer", people);

		return people;
	}
}
