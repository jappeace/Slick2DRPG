package org.bakkes.game.model.entity.follower.state;

import org.bakkes.game.model.entity.follower.FollowingPokemon;

public interface IState {
	void enter(FollowingPokemon pokemon);
	void execute(FollowingPokemon pokemon);
	void exit(FollowingPokemon pokemon);
	
	String getName();
}
