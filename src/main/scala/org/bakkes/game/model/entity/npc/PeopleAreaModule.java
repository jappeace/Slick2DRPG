package org.bakkes.game.model.entity.npc;

import com.google.inject.Provides;
import org.bakkes.game.controller.init.scripting.dsl.area.NpcDsl;
import org.bakkes.game.controller.init.scripting.loader.CurrentAreaLoader;
import org.bakkes.game.model.entity.AAreaModule;
import org.bakkes.game.model.map.BlockedTileTracker;
import org.bakkes.game.model.map.IAreaNameAcces;

import java.util.Collection;
import java.util.LinkedList;

public class PeopleAreaModule extends AAreaModule{

	private final Collection<Person> people = new LinkedList<>();
	@Provides Collection<Person> providePeople(
        final NpcDsl npcdsl,
        final IAreaNameAcces areaNameHolder,
        final BlockedTileTracker tracker,
        final CurrentAreaLoader loader
    ){
		if(!isNewArea(areaNameHolder)){
			return people;
		}
		people.clear();
		npcdsl.setPeople(people);
		loader.loadNPC(npcdsl);

        tracker.putBlockedTiles("npc layer", people);

		return people;
	}
}
