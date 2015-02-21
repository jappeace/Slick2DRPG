package org.bakkes.game.model.pokemon

import nl.jappieklooster.scala.{TMinus, TPlus}

case class PokemonStatistics(
	health: Int,
	attack: Int,
	defence: Int,
	speed: Int
) extends IPokemonStatistics with TPlus[IPokemonStatistics] with TMinus[IPokemonStatistics]{

	def this() {
		this(10,1,1,1)
	}

	/**
	 * copy constructor
	 * @param stats
	 */
	def this(stats: IPokemonStatistics) {
		this(
			stats.getHealth,
			stats.getAttack,
			stats.getDefence,
			stats.getSpeed
		)
	}

	override def getHealth: Int = health
	override def getAttack: Int = attack
	override def getDefence: Int = defence
	override def getSpeed: Int = speed

	override def plus(rightHand: IPokemonStatistics): IPokemonStatistics = {
		return   copy(
					health = rightHand.getHealth + health,
					attack = rightHand.getAttack + attack,
					defence = rightHand.getDefence + defence,
					speed = rightHand.getSpeed + speed
				)
	}

	def minus(rightHand: IPokemonStatistics): IPokemonStatistics = {
		return   copy(
			health = rightHand.getHealth - health,
			attack = rightHand.getAttack - attack,
			defence = rightHand.getDefence - defence,
			speed = rightHand.getSpeed - speed
		)
	}
}