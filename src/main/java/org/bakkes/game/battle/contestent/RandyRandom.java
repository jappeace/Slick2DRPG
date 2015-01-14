package org.bakkes.game.battle.contestent;

import java.util.Random;

import org.bakkes.game.model.battle.move.IMove;

import com.google.inject.Inject;


public class RandyRandom extends AI{

	@Inject Random random;
	@Override
	protected IMove getMove() {
		return ownPokemon.getMoves().get(random.nextInt(ownPokemon.getMoves().size()));
	}

}
