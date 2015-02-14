package org.bakkes.game.controller.state;

import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.state.transition.Transition;

import com.google.inject.Inject;

public class StateManager {
	private @Inject StateBasedGame game;
	private Transition leave = new FadeOutTransition();
	private Transition enter = new FadeInTransition();
	public void enter(final State to){
		game.enterState(to.ordinal(), leave, enter);
	}
	public GameState get(final State which){
		return game.getState(which.ordinal());
	}
	public final void setLeave(final Transition leave) {
		this.leave = leave;
	}
	public final void setEnter(final Transition enter) {
		this.enter = enter;
	}
}
