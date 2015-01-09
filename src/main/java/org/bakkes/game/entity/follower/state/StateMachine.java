package org.bakkes.game.entity.follower.state;

import org.bakkes.game.entity.follower.FollowingPokemon;
import org.bakkes.game.scripting.ScriptManager;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

public class StateMachine {

	private FollowingPokemon follower;
	private IState currentState;
	
	public StateMachine(FollowingPokemon follower) {
		this.follower = follower;
		changeState(LoadDefaultState());
	}
	
	public void update() {
		currentState.execute(follower);
	}
	
	public void changeState(IState newState) {
		if(currentState != null)
			currentState.exit(follower);
		newState.enter(follower);
		
		currentState = newState;
	}
	
	public IState getState() {
		return currentState;
	}
	
	public static IState LoadDefaultState() {
		PythonInterpreter intp = ScriptManager.getInterpreter();
		PyObject followerState = intp.get("FollowerState");
		PyObject stateInstance = followerState.__call__();
		return (IState)stateInstance.__tojava__(IState.class);
	}
}
