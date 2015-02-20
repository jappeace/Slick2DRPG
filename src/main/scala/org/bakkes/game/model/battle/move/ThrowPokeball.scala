package org.bakkes.game.model.battle.move

import org.bakkes.game.model.pokemon.Pokemon

class ThrowPokeball extends IMove{
	private var success:Boolean = false

	override def execute(self: Pokemon, other: Pokemon): Unit = {

		other.getSpecies().getCatchChacne()
	}

	override def isBattleOver: Boolean = success

	override def getName: String = "pokeball"
}
