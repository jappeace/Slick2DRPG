package org.bakkes.game.model.battle.move

import com.google.inject.Inject
import com.google.inject.name.Named
import org.bakkes.game.model.entity.player.invetory.PokeBelt
import org.bakkes.game.model.pokemon.Pokemon
import org.newdawn.slick.util.Log

import scala.util.Random

class ThrowPokeball @Inject() (
	random:Random,
	@Named("from player")
	pokebelt:PokeBelt
) extends IMove{
	private var success:Boolean = false

	override def execute(self: Pokemon, other: Pokemon): Unit = {
		val lifePercentage = other.getCurrentStats.getHealth / other.getNormalStats.getHealth
		val role = random.nextFloat + lifePercentage
		val toCatchLowerThen = other.getSpecies.getCatchChance
		Log.info("trying to catch with a " + role
				+ " need to have lower then" + toCatchLowerThen)
		if (role < toCatchLowerThen){
			Log.info("you caught a " + other.getName)
			pokebelt.add(other)
			success = true
		}
	}

	override def isBattleOver: Boolean = success

	override def getName: String = "pokeball"
}
