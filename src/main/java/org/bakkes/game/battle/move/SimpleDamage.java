package org.bakkes.game.battle.move;

import org.bakkes.game.battle.IMoveExecutor;
import org.bakkes.game.model.pokemon.IMove;
import org.bakkes.game.model.pokemon.Pokemon;

public class SimpleDamage implements IMoveExecutor{

	@Override
	public void executeMove(final IMove move, final Pokemon argressor, final Pokemon target) {
		target.damage(move.getDamage());
	}

}
