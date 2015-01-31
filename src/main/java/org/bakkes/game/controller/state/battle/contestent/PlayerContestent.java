package org.bakkes.game.controller.state.battle.contestent;

import org.bakkes.game.model.battle.move.IMove;
import org.bakkes.game.model.pokemon.Pokemon;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class PlayerContestent extends AContestent {

	@Inject @Named("own")
	private Pokemon ownPokemon;
	@Inject @Named("target")
	private Pokemon targetPokemon;

	IMove currentMove = null;
	public synchronized void setMove(final IMove move){
		currentMove = move;
	}

	public synchronized void selectMove(final int which){
		setMove(this.ownPokemon.getMoves().get(which));
	}
	@Override
	public synchronized boolean isReady() {
		return currentMove != null;
	}

	@Override
	protected synchronized IMove getMove() {
		final IMove result = currentMove;
		currentMove = null;
		return result;
	}

	@Override
	public Pokemon getOwn() {
		return ownPokemon;
	}

	@Override
	protected Pokemon getTarget() {
		return targetPokemon;
	}

}
