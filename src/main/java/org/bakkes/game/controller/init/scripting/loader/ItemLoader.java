package org.bakkes.game.controller.init.scripting.loader;

import org.bakkes.game.R;
import org.bakkes.game.controller.init.scripting.dsl.ItemDefinitionDsl;
import org.bakkes.game.model.entity.player.invetory.Item;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class ItemLoader {
	private @Inject Provider<ItemDefinitionDsl> itemDslProvider;
	private @Inject ScriptLoader loader;

	public Item load(final String name){
        final ItemDefinitionDsl dsl = itemDslProvider.get();
        loader.load(R.itemScripts + name + ".dsl", dsl);
        dsl.setItemName(name);
        return dsl.getItem();
	}
}
