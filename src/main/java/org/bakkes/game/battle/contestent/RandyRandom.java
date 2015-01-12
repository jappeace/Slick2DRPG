package org.bakkes.game.battle.contestent;

import org.bakkes.game.GameInfo;
import org.bakkes.game.model.battle.move.IMove;


public class RandyRandom extends AI{

	@Override
	protected IMove getMove() {
		return ownPokemon.getMoves().get(GameInfo.RANDOM.nextInt(ownPokemon.getMoves().size()));
	}

}
