package org.bakkes.game.controller.init;

import java.nio.file.Path;

import org.bakkes.game.controller.init.scripting.dsl.area.PlayerDsl;
import org.bakkes.game.controller.init.scripting.loader.ScriptLoader;

import com.google.inject.Inject;
import com.google.inject.name.Named;

/**
 * most boring class ever
 */
public class PlayerLoader {
	@Inject ScriptLoader loader;
	@Inject PlayerDsl dsl;
	@Inject @Named("overworld") Path path;
	public void load(){
		loader.load(path.resolve("player.dsl"), dsl);
	}
}
