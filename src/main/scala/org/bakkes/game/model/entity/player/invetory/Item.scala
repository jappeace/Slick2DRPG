package org.bakkes.game.model.entity.player.invetory

import com.google.inject.Inject
import org.bakkes.game.model.entity.OverworldEntity

import scala.beans.BeanProperty

case class  Item  (itemName:String) extends OverworldEntity{
	setName(itemName)
	@Inject()
	def this() = this("")
	@BeanProperty
	var amount:Int = 1
}

