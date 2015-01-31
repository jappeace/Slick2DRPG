package org.bakkes.game.model.entity.npc;

import java.util.Collection;
import java.util.LinkedList;

import org.bakkes.game.controller.init.scripting.dsl.area.NpcDsl;
import org.bakkes.game.controller.init.scripting.loader.ScriptLoader;
import org.bakkes.game.model.entity.AAreaModule;
import org.bakkes.game.model.map.BlockedTileTracker;
import org.bakkes.game.model.map.IAreaNameAcces;

import com.google.inject.Provides;

public class PeopleAreaModule extends AAreaModule{

	private final Collection<Person> people = new LinkedList<>();
	@Provides Collection<Person> providePeople(
        final NpcDsl npcdsl,
        final ScriptLoader scriptloader,
        final IAreaNameAcces areaNameHolder,
        final BlockedTileTracker tracker
    ){
		if(!isNewArea(areaNameHolder)){
			return people;
		}
		people.clear();
		npcdsl.setPeople(people);
		scriptloader.load(getScriptFolder() + "npc.dsl", npcdsl);

        tracker.putBlockedTiles("npc layer", people);

		return people;
	}
}
