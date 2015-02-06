package org.bakkes.game.controller.state.battle;

import java.util.List;

import org.bakkes.game.AModule;
import org.bakkes.game.controller.state.battle.contestent.IContestent;
import org.bakkes.game.controller.state.battle.contestent.PlayerContestent;
import org.bakkes.game.controller.state.battle.contestent.RandyRandom;
import org.bakkes.game.model.battle.BattleLogEvent;
import org.bakkes.game.model.pokemon.Pokemon;

import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

public class BattleModule extends AModule{

	@Override
	public void configure(){
		bind(IContestent.class).to(RandyRandom.class);
		bind(PlayerContestent.class).in(Singleton.class);
	}
	public @Provides IContestent[] provideContestents(
			final IContestent ai,
			final PlayerContestent player,
            @Named("current players") final Pokemon playerPoke,
            @Named("current enemys") final Pokemon enemyPoke
        ){
		final IContestent[] result = new IContestent[2];
		result[0] = ai;
		result[1] = player;
		ai.setOwnPokemon(enemyPoke);
		ai.setTargetPokemon(playerPoke);
		player.setOwnPokemon(playerPoke);
		player.setTargetPokemon(enemyPoke);
		return result;
	}
	public @Provides List<BattleLogEvent> provideLog(final BattleLoader loader){
		return loader.getCurrentLog();
	}


}
