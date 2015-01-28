package org.bakkes.game.controller.state;

import java.util.Collection;
import java.util.LinkedList;

import org.bakkes.game.AModule;
import org.bakkes.game.controller.IController;
import org.bakkes.game.controller.MessageBoxController;

import com.google.inject.Provides;
import com.google.inject.Singleton;

public class StateModule extends AModule{
	@Override
	protected void configure() {
		bind(CommonGameState.class).to(OverworldState.class);
		bind(OverworldState.class).in(Singleton.class);
		bind(BattleState.class).in(Singleton.class);
	}


	public @Provides Collection<IController> provideControllers(
        final LinkedList<IController> result,
        final MessageBoxController msgBoxes
    ){
		result.add(msgBoxes);
		return result;
    }
}
