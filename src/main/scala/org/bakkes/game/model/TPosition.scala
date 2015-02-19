package org.bakkes.game.model

import org.newdawn.slick.geom.Vector2f
trait TPosition extends IPositionable{

	private val _position:Vector2f  = new Vector2f()
	override def getPosition = _position

	def position = getPosition
	def position_= (to:Vector2f) = setPosition(to)

	override def x = getPosition().x
	override def y = getPosition().y
	override def x(to:Float) = {
		getPosition.x = to
		onChangePosition(getPosition())
	}
	override def y(to:Float) = {
		getPosition.y = to
		onChangePosition(getPosition())
	}
	override def setPosition(position: Vector2f ) = {
		_position.x = position.x
		_position.y = position.y
		onChangePosition(getPosition())
	}

    /**
     * when the location changed this method is called,
     * use this to update your composite view
     * @param position
     */
	override def onChangePosition(newLocation:Vector2f) = {}
}