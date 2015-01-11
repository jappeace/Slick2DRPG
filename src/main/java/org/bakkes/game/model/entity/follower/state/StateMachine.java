package org.bakkes.game.model.entity.follower.state;

import org.bakkes.game.model.entity.follower.FollowingPokemon;

public class StateMachine {

	private FollowingPokemon follower;
	private IState currentState;

	public StateMachine(final FollowingPokemon follower) {
		this.follower = follower;
		changeState(
				new IState(){
					@Override
					public void enter(final FollowingPokemon pokemon) {
					}
					@Override
					public void execute(final FollowingPokemon pokemon) {
					}
					@Override
					public void exit(final FollowingPokemon pokemon) {
					}
					@Override
					public String getName() {
						return "No state";
					}
				}
        );
	}

	public void update() {
		currentState.execute(follower);
	}

	public void changeState(final IState newState) {
		if(currentState != null)
			currentState.exit(follower);
		newState.enter(follower);

		currentState = newState;
	}

	public IState getState() {
		return currentState;
	}
}
