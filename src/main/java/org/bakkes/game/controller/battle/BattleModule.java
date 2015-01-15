package org.bakkes.game.controller.battle;

import java.util.ArrayList;
import java.util.List;

import org.bakkes.game.controller.battle.contestent.IContestent;
import org.bakkes.game.controller.battle.contestent.PlayerContestent;
import org.bakkes.game.model.battle.BattleLogEvent;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

public class BattleModule extends AbstractModule{

	private PlayerContestent player;
	public BattleModule(final PlayerContestent player){
		this.player = player;
	}
	@Override
	protected void configure() {
	}
	public @Provides IContestent[] provideContestents(final IContestent ai){
		final IContestent[] result = new IContestent[2];
		result[0] = ai;
		result[1] = player;
		return result;
	}
	public @Singleton @Provides List<BattleLogEvent> provideLog(){
		return new ArrayList<>();
	}


}
