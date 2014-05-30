package org.bakkes.game.entity.follower.state;

import org.bakkes.game.entity.follower.FollowingPokemon;

public interface State {
	void Enter(FollowingPokemon pokemon);
	void Execute(FollowingPokemon pokemon);
	void Exit(FollowingPokemon pokemon);
}
