package org.bakkes.game.model

abstract class AModel extends IModel {
	var name: String = ""
	override def toString: String = {
		this.getClass.getSimpleName + " "  + this.getName + " "
	}

	override def getName = name
	override def setName(to:String) = name = to
}