package org.bakkes.game.controller.state.battle;

import org.bakkes.game.controller.init.scripting.dsl.area.WildDsl;
import org.bakkes.game.controller.init.scripting.loader.ScriptLoader;
import org.bakkes.game.model.pokemon.Pokemon;

import com.google.inject.Inject;

public class WildLoader implements IBattleLoader {

	@Inject ScriptLoader loader;
	@Inject WildDsl dsl;
	@Override
	public Pokemon getEnemy() {
		return null;
	}

}
