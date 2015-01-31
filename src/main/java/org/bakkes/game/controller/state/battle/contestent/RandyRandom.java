package org.bakkes.game.controller.state.battle.contestent;

import java.util.Random;

import org.bakkes.game.model.battle.move.IMove;

import com.google.inject.Inject;


public class RandyRandom extends AI{

	@Inject Random random;
	@Override
	protected IMove getMove() {
		return getOwn().getMoves().get(random.nextInt(getOwn().getMoves().size()));
	}

}
