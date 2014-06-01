package org.bakkes.game.entity.follower.state;

import org.bakkes.game.entity.follower.FollowingPokemon;

public interface State {
	void enter(FollowingPokemon pokemon);
	void execute(FollowingPokemon pokemon);
	void exit(FollowingPokemon pokemon);
	
	String getName();
}
