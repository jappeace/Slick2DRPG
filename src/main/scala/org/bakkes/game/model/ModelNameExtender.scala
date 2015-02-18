package org.bakkes.game.model

class ModelNameExtender extends IModel{

	val nameExtender = new TStringExtender(){
		override def target = model.getName
	}
	private var model:IModel = null

	def setModel(to:IModel) = model = to
	override def setName(to: String) = model.setName(to)
	override def getName = nameExtender.extend
}
