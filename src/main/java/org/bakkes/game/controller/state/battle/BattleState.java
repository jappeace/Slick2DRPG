package org.bakkes.game.controller.state.battle;

import org.bakkes.game.controller.async.IThreadPool;
import org.bakkes.game.controller.event.input.CompositeKeyListener;
import org.bakkes.game.controller.state.CommonGameState;
import org.bakkes.game.controller.state.State;
import org.bakkes.game.controller.state.StateManager;
import org.bakkes.game.model.entity.player.invetory.PokeBelt;
import org.bakkes.game.view.battle.BattleView;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;

public class BattleState extends CommonGameState {

	private @Inject IThreadPool pool;
	private @Inject BattleLoader loader;
	private @Inject Provider<BattleView> battleViewProvider;
	private @Inject @Named("battle") Provider<CompositeKeyListener> keyListener;
	private @Inject @Named("from player") PokeBelt belt;
	private @Inject StateManager states;
	@Override
	public void enter() {
		if(belt.getFirstAlive() == null){
			states.enter(State.Overworld);
			return;
		}
		this.clearRenderables();
		this.addRenderable(battleViewProvider.get());
		this.setKeyListener(keyListener.get());
		pool.execute(loader.getCurrentBattle());
	}

	public void setType(final BattleType to){
		loader.loadBattle(to);
	}

	@Override
	public int getID() {
		return State.Battle.ordinal();
	}
}
