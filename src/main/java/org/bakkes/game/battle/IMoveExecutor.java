package org.bakkes.game.battle;

import org.bakkes.game.model.pokemon.IMove;
import org.bakkes.game.model.pokemon.Pokemon;

public interface IMoveExecutor {
	/**
	 * execute a move
	 * @param move
	 * @param argressor
	 * @param target
	 */
	public void executeMove(IMove move, Pokemon argressor, Pokemon target);
}
