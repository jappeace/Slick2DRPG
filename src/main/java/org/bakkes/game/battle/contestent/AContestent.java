package org.bakkes.game.battle.contestent;

import org.bakkes.game.battle.IContestent;
import org.bakkes.game.model.battle.Turn;
import org.bakkes.game.model.battle.move.IMove;
import org.bakkes.game.model.pokemon.Pokemon;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public abstract class AContestent implements IContestent{

	@Inject @Named("own") protected Pokemon ownPokemon;
	@Inject @Named("target") private Pokemon targetPokemon;

	@Override
	public Turn getTurn() {
		final Turn result = new Turn();
		result.setTarget(targetPokemon);
		result.setAgressor(ownPokemon);
		result.setMove(getMove());
		return result;
	}

	protected abstract IMove getMove();

	@Override
	public Pokemon getPokemon() {
		return ownPokemon;
	}
}
