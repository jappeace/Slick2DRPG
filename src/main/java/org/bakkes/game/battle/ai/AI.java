package org.bakkes.game.battle.ai;

import org.bakkes.game.battle.IContestent;
import org.bakkes.game.battle.Turn;
import org.bakkes.game.model.pokemon.IMove;
import org.bakkes.game.model.pokemon.Pokemon;

public abstract class AI implements IContestent{

	Pokemon ownPokemon;
	Pokemon targetPokemon;

	@Override
	public boolean isReady() {
		return true;
	}

	@Override
	public Turn getTurn() {
		final Turn result = new Turn();
		result.setTarget(targetPokemon);
		result.setAgressor(ownPokemon);
		result.setMove(getMove());
		return result;
	}

	abstract IMove getMove();

	@Override
	public Pokemon getPokemon() {
		return ownPokemon;
	}
}
