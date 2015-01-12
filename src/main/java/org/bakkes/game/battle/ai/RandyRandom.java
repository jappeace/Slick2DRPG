package org.bakkes.game.battle.ai;

import org.bakkes.game.GameInfo;
import org.bakkes.game.model.pokemon.IMove;


public class RandyRandom extends AI{

	@Override
	IMove getMove() {
		return ownPokemon.getMoves().get(GameInfo.RANDOM.nextInt(ownPokemon.getMoves().size()));
	}
}
