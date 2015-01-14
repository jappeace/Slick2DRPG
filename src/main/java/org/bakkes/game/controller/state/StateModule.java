package org.bakkes.game.controller.state;

import java.util.LinkedList;
import java.util.List;

import org.bakkes.game.controller.events.key.IKeyListener;
import org.bakkes.game.controller.events.key.MovementListener;
import org.bakkes.game.controller.events.key.TalkToNPCListener;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

public class StateModule extends AbstractModule{
	@Override
	protected void configure() {
		bind(CommonGameState.class).to(OverworldState.class);
	}

	@Provides List<IKeyListener> provideKeyListeners(final LinkedList<IKeyListener> linkedList, final TalkToNPCListener npc, final MovementListener movement){
		linkedList.add(npc);
		linkedList.add(movement);
		return linkedList;
	}

}
