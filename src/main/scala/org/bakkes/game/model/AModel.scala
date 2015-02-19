package org.bakkes.game.model

import scala.beans.BeanProperty

abstract class AModel(
	 @BeanProperty
	 var name: String
 ) extends IModel {
	def this() {
		this("anonymous")
	}

	override def toString: String = {
		this.getClass.getSimpleName + " "  + this.getName + " "
	}
}