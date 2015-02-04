package org.bakkes.game.model.entity.player.invetory;

import java.nio.file.Path;
import java.util.Collection;
import java.util.LinkedList;

import org.bakkes.game.controller.init.scripting.dsl.area.ItemDsl;
import org.bakkes.game.controller.init.scripting.loader.ScriptLoader;
import org.bakkes.game.model.entity.AAreaModule;
import org.bakkes.game.model.map.BlockedTileTracker;
import org.bakkes.game.model.map.IAreaNameAcces;

import com.google.inject.Provides;
import com.google.inject.name.Named;

public class ItemAreaModule extends AAreaModule{

	private Collection<Item> items = new LinkedList<>();

	@Provides Collection<Item> provideItems(
        final ItemDsl itemDsl,
        final ScriptLoader scriptloader,
        final IAreaNameAcces areaNameHolder,
        final BlockedTileTracker tracker,
        @Named("current area") final Path path
    ){
		if(!isNewArea(areaNameHolder)){
			return items;
		}
		items.clear();
		scriptloader.load(path.resolve("item.dsl"), itemDsl);
		items = itemDsl.getResult();
		tracker.putBlockedTiles("items", items);
		return items;
	}
}
