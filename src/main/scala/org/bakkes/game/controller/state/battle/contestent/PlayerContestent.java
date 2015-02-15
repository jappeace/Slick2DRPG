package org.bakkes.game.controller.state.battle.contestent;

import org.bakkes.game.model.battle.move.IMove;

import java.util.List;

public class PlayerContestent extends AContestent {

	IMove currentMove = null;
	public synchronized void setMove(final IMove move){
		currentMove = move;
	}

	public synchronized boolean selectMove(final int which){
		List<IMove> moves = getOwnPokemon().getMoves();
		if(which < 0){
			return false;
		}
		if(which >= moves.size()){
			return false;
		}
		setMove(moves.get(which));
		return true;
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
