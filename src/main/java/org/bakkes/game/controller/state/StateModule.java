package org.bakkes.game.controller.state;

import java.util.LinkedList;
import java.util.List;

import org.bakkes.game.controller.events.key.IKeyListener;
import org.bakkes.game.controller.events.key.MovementListener;
import org.bakkes.game.controller.events.key.TalkToNPCListener;
import org.newdawn.slick.GameContainer;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

public class StateModule extends AbstractModule{
	private GameContainer container;

	public StateModule(final GameContainer container){
		this.container = container;
	}
	@Override
	protected void configure() {
		// TODO Auto-generated method stub

	}

	@Provides List<IKeyListener> provideKeyListeners(final LinkedList<IKeyListener> linkedList, final TalkToNPCListener npc, final MovementListener movement){
		linkedList.add(npc);
		linkedList.add(movement);
		return linkedList;
	}

	@Singleton @Provides GameContainer provideGameContainer(){
		return container;
	}

}
