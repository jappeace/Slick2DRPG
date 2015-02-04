package org.bakkes.game.model.entity.npc;

import java.nio.file.Path;
import java.util.Collection;
import java.util.LinkedList;

import org.bakkes.game.controller.init.scripting.dsl.area.NpcDsl;
import org.bakkes.game.controller.init.scripting.loader.ScriptLoader;
import org.bakkes.game.model.entity.AAreaModule;
import org.bakkes.game.model.map.BlockedTileTracker;
import org.bakkes.game.model.map.IAreaNameAcces;

import com.google.inject.Provides;
import com.google.inject.name.Named;

public class PeopleAreaModule extends AAreaModule{

	private final Collection<Person> people = new LinkedList<>();
	@Provides Collection<Person> providePeople(
        final NpcDsl npcdsl,
        final ScriptLoader scriptloader,
        final IAreaNameAcces areaNameHolder,
        final BlockedTileTracker tracker,
        @Named("current area") final Path path
    ){
		if(!isNewArea(areaNameHolder)){
			return people;
		}
		people.clear();
		npcdsl.setPeople(people);
		scriptloader.load(path.resolve("npc.dsl"), npcdsl);

        tracker.putBlockedTiles("npc layer", people);

		return people;
	}
}
