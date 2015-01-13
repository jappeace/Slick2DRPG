package org.bakkes.game.battle.contestent;

import org.bakkes.game.model.battle.move.IMove;

public class PlayerContestent extends AContestent {

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

}
