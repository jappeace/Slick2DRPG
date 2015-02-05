package org.bakkes.game.controller.state.battle;

import java.util.Collection;
import java.util.LinkedList;

import org.bakkes.game.AModule;
import org.bakkes.game.controller.event.input.BattleEndListner;
import org.bakkes.game.controller.event.input.IKeyListener;
import org.bakkes.game.model.map.IAreaNameAcces;
import org.newdawn.slick.state.StateBasedGame;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Provides;

/**
 * bridging module
 */
public class BattleStateModule extends AModule{
	private @Inject Provider<StateBasedGame> game;
    private @Inject Provider<IAreaNameAcces> named;
    @Override
	public void configure(){
    }
	@Provides
	StateBasedGame provideGame(){
		return game.get();
	}
	@Provides
	IAreaNameAcces provideAcces(){
		return named.get();
	}
	@Provides
    public Collection<IKeyListener> providesKeyListeners(
    		final LinkedList<IKeyListener> result,
    		final BattleEndListner listener
        ){
    	result.add(listener);
    	return result;
    }
}
