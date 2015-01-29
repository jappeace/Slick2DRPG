package org.bakkes.game.controller.state;

import java.util.Collection;
import java.util.LinkedList;

import org.bakkes.game.AModule;
import org.bakkes.game.controller.IUpdatable;
import org.bakkes.game.controller.MessageBoxController;
import org.bakkes.game.controller.async.ThreadBridger;

import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.google.inject.name.Names;

public class StateModule extends AModule{
	@Override
	protected void configure() {
		bind(CommonGameState.class).to(OverworldState.class);
		bind(OverworldState.class).in(Singleton.class);
		bind(BattleState.class).in(Singleton.class);
		bind(ThreadBridger.class).annotatedWith(Names.named("in mainthread")).to(ThreadBridger.class).in(Singleton.class);
	}


	public @Provides Collection<IUpdatable> provideControllers(
        final LinkedList<IUpdatable> result,
        final MessageBoxController msgBoxes,
        @Named("in mainthread") final ThreadBridger threadBridger
    ){
		result.add(msgBoxes);
		result.add(threadBridger);
		return result;
    }
}
