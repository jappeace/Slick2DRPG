package org.bakkes.game.controller.init.scripting.loader;

import java.nio.file.Path;

import org.bakkes.game.controller.init.scripting.dsl.ItemDefinitionDsl;
import org.bakkes.game.model.entity.player.invetory.Item;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;

public class ItemLoader {
	private @Inject Provider<ItemDefinitionDsl> itemDslProvider;
	private @Inject ScriptLoader loader;
	private @Inject @Named("scriptItems") Path path;

	public Item load(final String name){
        final ItemDefinitionDsl dsl = itemDslProvider.get();
        loader.load(path.resolve(name + ".dsl"), dsl);
        dsl.setItemName(name);
        return dsl.getItem();
	}
}
