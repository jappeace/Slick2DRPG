package org.bakkes.game.controller.state;

import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.google.inject.name.Names;
import org.bakkes.game.AModule;
import org.bakkes.game.controller.IUpdatable;
import org.bakkes.game.controller.MessageBoxController;
import org.bakkes.game.controller.async.DelayedBatchExecutor;
import org.bakkes.game.controller.state.battle.BattleState;
import org.bakkes.game.controller.state.overworld.OverworldState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.Collection;
import java.util.LinkedList;

public class StateModule extends AModule{
	@Override
	protected void configure() {
		bind(OverworldState.class).in(Singleton.class);
		bind(BattleState.class).in(Singleton.class);
		bind(DelayedBatchExecutor.class).annotatedWith(Names.named("in mainthread")).to(DelayedBatchExecutor.class).in(Singleton.class);
	}


	public @Provides Collection<IUpdatable> provideControllers(
        final LinkedList<IUpdatable> result,
        final MessageBoxController msgBoxes,
        @Named("in mainthread") final DelayedBatchExecutor threadBridger
    ){
		result.add(msgBoxes);
		result.add(threadBridger);
		return result;
    }
	public @Provides CommonGameState provideState(final StateBasedGame game){
		return (CommonGameState)game.getCurrentState();
	}
}
