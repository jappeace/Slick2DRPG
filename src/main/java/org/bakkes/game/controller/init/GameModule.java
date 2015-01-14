package org.bakkes.game.controller.init;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Random;

import org.bakkes.game.controller.state.BattleState;
import org.bakkes.game.controller.state.OverworldState;
import org.newdawn.slick.state.GameState;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Names;

public class GameModule extends AbstractModule{
	private static final String GAME_TITLE = "jappie-mon";

	@Override
	protected void configure() {
		bind(org.newdawn.slick.Game.class).to(Game.class);
		bind(String.class).annotatedWith(Names.named("game-title")).toInstance(GAME_TITLE);
		bind(Random.class).in(Singleton.class);
	}
	public @Provides Collection<GameState> provideStates(final OverworldState overworld, final BattleState battle){
		final Collection<GameState> result = new LinkedList<>();
		result.add(overworld);
		result.add(battle);
		return result;
	}

}
