package org.bakkes.game.controller.state.battle.event.key;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.bakkes.game.controller.state.event.input.AKeyListener;
import org.bakkes.game.controller.state.event.input.Key;
import org.bakkes.game.controller.state.State;
import org.bakkes.game.controller.state.StateManager;
import org.bakkes.game.controller.state.battle.Battle;

public class BattleEndListner extends AKeyListener {

	@Inject StateManager states;
	@Inject @Named("current") Battle battle;
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
