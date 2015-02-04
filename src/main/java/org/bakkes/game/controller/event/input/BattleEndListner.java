package org.bakkes.game.controller.event.input;

import org.bakkes.game.controller.state.State;
import org.bakkes.game.controller.state.StateManager;
import org.bakkes.game.controller.state.battle.Battle;

import com.google.inject.Inject;

public class BattleEndListner extends AKeyListener{

	@Inject StateManager states;
	@Inject Battle battle;
	@Override
	public void KeyDown(final Key key) {
		if(!battle.isOver()) {
			return;
		}
        if(key.isConfirm()){
            states.enter(State.Overworld);
        }
	}

}
