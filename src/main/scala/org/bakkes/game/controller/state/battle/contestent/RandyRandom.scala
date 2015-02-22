package org.bakkes.game.controller.state.battle.contestent

import java.util.Random

import com.google.inject.Inject
import org.bakkes.game.model.battle.move.IMove
import org.newdawn.slick.util.Log

class RandyRandom @Inject() (
	val random: Random
)extends AI {
	protected def getMove: IMove = {
		val own = getOwnPokemon.getMoves
		val choice = random.nextInt(own.length)
		Log.debug("ai choice " + choice + " from: " + getOwnPokemon.getMoves)
		return own.apply(choice)
	}
}