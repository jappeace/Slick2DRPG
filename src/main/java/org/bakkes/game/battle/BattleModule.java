package org.bakkes.game.battle;

import java.util.LinkedList;
import java.util.List;

import org.bakkes.game.battle.contestent.IContestent;
import org.bakkes.game.battle.contestent.PlayerContestent;
import org.bakkes.game.model.battle.Turn;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

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
	public @Provides List<Turn> provideLog(){
		return new LinkedList<>();
	}


}
