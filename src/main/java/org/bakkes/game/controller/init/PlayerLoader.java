package org.bakkes.game.controller.init;

import org.bakkes.game.R;
import org.bakkes.game.controller.init.scripting.dsl.area.PlayerDsl;
import org.bakkes.game.controller.init.scripting.loader.ScriptLoader;

import com.google.inject.Inject;

/**
 * most boring class ever
 */
public class PlayerLoader {
	@Inject ScriptLoader loader;
	@Inject PlayerDsl dsl;
	public void load(){
		loader.load(R.overworldScripts + "player.dsl", dsl);
	}
}
