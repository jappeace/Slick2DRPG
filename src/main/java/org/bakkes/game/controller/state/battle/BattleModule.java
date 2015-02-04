package org.bakkes.game.controller.state.battle;

import java.util.ArrayList;
import java.util.List;

import org.bakkes.game.AModule;
import org.bakkes.game.controller.state.battle.contestent.IContestent;
import org.bakkes.game.controller.state.battle.contestent.PlayerContestent;
import org.bakkes.game.controller.state.battle.contestent.RandyRandom;
import org.bakkes.game.model.battle.BattleLogEvent;

import com.google.inject.Provides;
import com.google.inject.Singleton;

public class BattleModule extends AModule{

	private PlayerContestent player;
	public BattleModule(final PlayerContestent player){
		this.player = player;
	}
	@Override
	public void configure(){
		bind(IContestent.class).to(RandyRandom.class);
	}
	public @Provides PlayerContestent provideContestent(){
		return player;
	}
	public @Provides IContestent[] provideContestents(final IContestent ai){
		final IContestent[] result = new IContestent[2];
		result[0] = ai;
		result[1] = player;
		ai.setOwnPokemon(player.getTargetPokemon());
		ai.setTargetPokemon(player.getOwnPokemon());
		return result;
	}
	public @Singleton @Provides List<BattleLogEvent> provideLog(){
		return new ArrayList<>();
	}


}
