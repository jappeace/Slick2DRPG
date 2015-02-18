package org.bakkes.game.controller.init.scripting.loader;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;
import org.bakkes.game.controller.init.scripting.dsl.ItemDefinitionDsl;
import org.bakkes.game.model.entity.player.invetory.Item;

import java.nio.file.Path;

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
