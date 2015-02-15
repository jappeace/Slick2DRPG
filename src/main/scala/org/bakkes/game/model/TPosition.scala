package org.bakkes.game.model

import org.newdawn.slick.geom.Vector2f
trait TPosition extends IPositionable{

	override def x():Float = getPosition().x
	override def y():Float = getPosition().y
	override def x(to:Float) = {
		getPosition.x = to
		onChangePosition(getPosition())
	}	
	override def y(to:Float) = {
		getPosition.y = to
		onChangePosition(getPosition())
	}	
	override def setPosition(position: Vector2f ) = {
		x(position.x);
		y(position.y);
	}

    /**
     * when the location changed this method is called,
     * use this to update your composite view
     * @param position
     */
	override def onChangePosition(newLocation:Vector2f) = {}
}