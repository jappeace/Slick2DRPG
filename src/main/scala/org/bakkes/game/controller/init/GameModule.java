package org.bakkes.game.controller.init;

import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import org.bakkes.game.AModule;
import org.bakkes.game.controller.state.battle.BattleState;
import org.bakkes.game.controller.state.overworld.OverworldState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Random;

public class GameModule extends AModule{

	private static final String GAME_TITLE = "jappie-mon";

	@Override
	protected void configure() {
		bind(org.newdawn.slick.Game.class).to(StateBasedGame.class).in(Singleton.class);
		bind(StateBasedGame.class).to(Game.class).in(Singleton.class);
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
