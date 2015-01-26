package org.bakkes.game.controller.state;

import java.util.LinkedList;
import java.util.List;

import org.bakkes.game.AModule;
import org.bakkes.game.controller.events.key.IKeyListener;
import org.bakkes.game.controller.events.key.InventoryToggleListener;
import org.bakkes.game.controller.events.key.MovementListener;
import org.bakkes.game.controller.events.key.InteractionListener;

import com.google.inject.Provides;
import com.google.inject.Singleton;

public class StateModule extends AModule{
	@Override
	protected void configure() {
		bind(CommonGameState.class).to(OverworldState.class);
		bind(OverworldState.class).in(Singleton.class);
		bind(BattleState.class).in(Singleton.class);
	}

	@Provides List<IKeyListener> provideKeyListeners(
        final LinkedList<IKeyListener> linkedList,
        final InteractionListener npc,
        final MovementListener movement,
        final InventoryToggleListener inventory
    ){

		linkedList.add(npc);
		linkedList.add(movement);
		linkedList.add(inventory);
		return linkedList;
	}

}
