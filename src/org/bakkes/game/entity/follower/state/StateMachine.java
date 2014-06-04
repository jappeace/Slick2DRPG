package org.bakkes.game.entity.follower.state;

import org.bakkes.game.entity.follower.FollowingPokemon;
import org.bakkes.game.scripting.ScriptManager;

public class StateMachine {

	private FollowingPokemon follower;
	private IState oldState;
	public StateMachine(FollowingPokemon follower) {
		this.follower = follower;
		ScriptManager.executeFunction("load_state", follower);
	}
	
	public void update() {
		oldState.execute(follower);
	}
	
	public void changeState(IState newState) {
		if(oldState != null)
			oldState.exit(follower);
		newState.enter(follower);
		
		oldState = newState;
	}
	
	public static void LoadDefaultState() {
		
	}
}
