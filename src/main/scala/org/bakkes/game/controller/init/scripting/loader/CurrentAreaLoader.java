package org.bakkes.game.controller.init.scripting.loader;

import java.nio.file.Path;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

@Singleton
public class CurrentAreaLoader {

	@Inject ScriptLoader loader;
	@Inject @Named("current area") Provider<Path> path;

	public void loadNPC(final IScriptLoadableType type){
		loader.load(path.get().resolve("npc.dsl"), type);
	}
	public void loadItems(final IScriptLoadableType type){
		loader.load(path.get().resolve("items.dsl"), type);
	}
	public void loadWild(final IScriptLoadableType type){
		loader.load(path.get().resolve("wild.dsl"), type);
	}
}
