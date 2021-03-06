package org.bakkes.game.controller.state.battle;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;
import org.bakkes.game.controller.init.scripting.dsl.area.WildDsl;
import org.bakkes.game.controller.init.scripting.loader.CurrentAreaLoader;
import org.bakkes.game.model.battle.BattleLogEvent;
import org.bakkes.game.model.entity.player.invetory.PokeBelt;
import org.bakkes.game.model.pokemon.Pokemon;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * loads and keeps track of the current battleing pokemon
 *
 * used in the modules to configure the proper injects (changing based on the current battle)
 */
public class BattleLoader {

	private int battleID = Integer.MIN_VALUE;
	private @Inject Provider<WildDsl> dslProvider;
	private @Inject CurrentAreaLoader loader;
	private @Inject Random random;
	private @Inject @Named("from player") PokeBelt playerBelt;
	private @Inject Provider<LinkedList<BattleLogEvent>> logProvider;
	private @Inject Provider<Battle> battleProvider;

	private Pokemon currentPlayerPokemon;
	private Pokemon	currentEnemyPokemon;
	private List<BattleLogEvent> currentLog;
	private Battle currentBattle;

	public void loadBattle(final BattleType type) {
		battleID ++;
		currentPlayerPokemon = playerBelt.getFirstAlive();
		currentLog = logProvider.get();
		switch(type){
		case Wild:
			final WildDsl dsl = dslProvider.get();
            loader.loadWild(dsl);
            currentEnemyPokemon = dsl.getResult().get(random.nextInt(dsl.getResult().size()));
            break;
		default:
			break;
		}

		// has to be last
		currentBattle = battleProvider.get();
	}

	public final Pokemon getCurrentPlayerPokemon() {
		return currentPlayerPokemon;
	}

	public final Pokemon getCurrentEnemyPokemon() {
		return currentEnemyPokemon;
	}

	public final List<BattleLogEvent> getCurrentLog(){
		return currentLog;
	}

	public final Battle getCurrentBattle(){
		return currentBattle;
	}

	public int getBattleID() {
		return battleID;
	}

}
