package org.bakkes.game.controller.state.battle;

import java.util.Random;

import org.bakkes.game.controller.init.scripting.dsl.area.WildDsl;
import org.bakkes.game.controller.init.scripting.loader.CurrentAreaLoader;
import org.bakkes.game.model.pokemon.Pokemon;

import com.google.inject.Inject;

public class WildLoader implements IBattleLoader {

	@Inject WildDsl dsl;
	@Inject CurrentAreaLoader loader;
	@Inject Random random;
	@Override
	public Pokemon getEnemy() {
		loader.loadWild(dsl);
		return dsl.getResult().get(random.nextInt(dsl.getResult().size()));
	}

}
