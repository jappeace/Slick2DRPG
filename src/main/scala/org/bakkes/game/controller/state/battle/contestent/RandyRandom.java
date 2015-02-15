package org.bakkes.game.controller.state.battle.contestent;

import java.util.List;
import java.util.Random;

import org.bakkes.game.model.battle.move.IMove;

import com.google.inject.Inject;


public class RandyRandom extends AI{

	@Inject Random random;
	@Override
	protected IMove getMove() {
		final List<IMove> own = getOwnPokemon().getMoves();
		return own.get(random.nextInt(own.size()));
	}

}