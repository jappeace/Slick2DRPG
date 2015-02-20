package org.bakkes.game.model.battle.move

import org.bakkes.game.model.pokemon.Pokemon

import scala.beans.BeanProperty

case class PlainDamage(
	@BeanProperty
	val name:String,
	val amount:Int
) extends IMove {

	override def execute(self: Pokemon, other: Pokemon): Unit = other.damage(amount)
	override def isBattleOver: Boolean = false
}