package org.bakkes.game.model

import nl.jappieklooster.scala.{TPlus, TMultiply, TMinus}
import org.newdawn.slick.geom.Vector2f


/**
 * TODO: replace with a proper vector and drop the one from slick2d and use implicit conversion
 * @param leftSide
 */
case class VectorOperated(leftSide:Vector2f) extends TMinus[Vector2f] with TMultiply[Vector2f] with TPlus[Vector2f]{
	override def minus(rightSide: Vector2f): Vector2f = {
		leftSide.copy().sub(rightSide)
	}
	override def plus(rightSide: Vector2f): Vector2f =  {
		leftSide.copy().add(rightSide)
	}
	override def multiply(rightSide: Vector2f): Vector2f = {
		new Vector2f(leftSide.x * rightSide.x, leftSide.y * rightSide.y)
	}
}
object Operator{
	implicit def toOperated(input:Vector2f) : VectorOperated = VectorOperated(input)
	implicit def toVector(input:VectorOperated) : Vector2f = input.leftSide
}
