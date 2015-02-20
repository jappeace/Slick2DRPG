package org.bakkes.game.model.battle.move;


import org.bakkes.game.model.INamed;
import org.bakkes.game.model.pokemon.Pokemon;

public interface IMove extends INamed{
	void execute(Pokemon self, Pokemon other);

	/**
	 * the default way of detecting that a battle is over is by checking if
	 * the health of either contestent is below 0
	 * @return
	 */
	boolean isBattleOver();
}
