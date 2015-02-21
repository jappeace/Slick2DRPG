package org.bakkes.game.model.pokemon

import org.bakkes.game.model.battle.move.{IMove, PlainDamage}
import org.bakkes.game.model.pokemon.Type._

import scala.beans.BeanProperty

case class PokemonSpecies(
	@BeanProperty
	name:String,
	@BeanProperty
	evolution: Option[IPokemonSpecies],
	@BeanProperty
	evolutionLevel:Int,
	@BeanProperty
	base: PokemonStatistics,
	@BeanProperty
	increase: PokemonStatistics,
	@BeanProperty
	trainingSpeed: Float,
	element: Type, // avoid keyword type, however interface needs a getType
	@BeanProperty
	moves: Seq[IMove],
	@BeanProperty
	catchChance: Float

) extends IPokemonSpecies{

	/**
	 * empty string means no evolution (final form)
	 */

	def this() {
		this(
			name = "caterpie",
			evolution = None,
			evolutionLevel = -1,
			base = new PokemonStatistics(),
			increase = new PokemonStatistics(),
			trainingSpeed = 1f,
			element = unkown,
			moves = List[IMove](PlainDamage("tackle", 10)),
			catchChance = 0.2f
		)
	}
	override def getType: Type = element
}
