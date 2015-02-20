package org.bakkes.game.controller.init.scripting.loader;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

import java.nio.file.Path;

@Singleton
public class CurrentAreaLoader {

	@Inject ScriptLoader loader;
	@Inject @Named("current area") Provider<Path> path;

	public void loadNPC(final Object type){
		loader.load(path.get().resolve("npc.dsl"), type);
	}
	public void loadItems(final Object type){
		loader.load(path.get().resolve("items.dsl"), type);
	}
	public void loadWild(final Object type){
		loader.load(path.get().resolve("wild.dsl"), type);
	}
}
