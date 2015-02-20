package org.bakkes.game.controller.init.scripting.dsl

import org.bakkes.game.model.battle.move.{PlainDamage, IMove}

import scala.beans.BeanProperty

class MoveDsl(
	@BeanProperty
	val name:String,
	@BeanProperty
	var damage:Int
) {
	def this(name:String) {
		this(name, 10)
	}
	def createMove():IMove = PlainDamage(name,damage)
}
