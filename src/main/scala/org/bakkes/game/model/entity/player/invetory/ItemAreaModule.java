package org.bakkes.game.model.entity.player.invetory;

import com.google.inject.Provides;
import org.bakkes.game.controller.init.scripting.dsl.area.ItemDsl;
import org.bakkes.game.controller.init.scripting.loader.CurrentAreaLoader;
import org.bakkes.game.model.entity.AAreaModule;
import org.bakkes.game.model.map.BlockedTileTracker;
import org.bakkes.game.model.map.IAreaNameAcces;

import java.util.Collection;
import java.util.LinkedList;

public class ItemAreaModule extends AAreaModule{

	private Collection<Item> items = new LinkedList<>();

	@Provides Collection<Item> provideItems(
        final ItemDsl itemDsl,
        final IAreaNameAcces areaNameHolder,
        final BlockedTileTracker tracker,
        final CurrentAreaLoader loader
    ){
		if(!isNewArea(areaNameHolder)){
			return items;
		}
		items.clear();
		loader.loadItems(itemDsl);
		items = itemDsl.getResult();
		tracker.putBlockedTiles("items", items);
		return items;
	}
}
