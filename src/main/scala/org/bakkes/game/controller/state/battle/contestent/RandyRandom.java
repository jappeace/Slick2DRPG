package org.bakkes.game.controller.state.battle.contestent;

import com.google.inject.Inject;
import org.bakkes.game.model.battle.move.IMove;

import java.util.List;
import java.util.Random;


public class RandyRandom extends AI{

	@Inject Random random;
	@Override
	protected IMove getMove() {
		final List<IMove> own = getOwnPokemon().getMoves();
		return own.get(random.nextInt(own.size()));
	}

}
