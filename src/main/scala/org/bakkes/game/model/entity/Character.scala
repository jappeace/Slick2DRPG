package org.bakkes.game.model.entity

import org.bakkes.game.model.map.Direction
import org.bakkes.game.model.map.Direction._
import org.bakkes.game.model.map.Tile

import scala.beans.BeanProperty

abstract class Character(
	@BeanProperty
	var facing: Direction
) extends OverworldEntity {

	def this(){
		this(South)
	}
	private var _isWalking: Boolean = false

	def onEnterNewTile = _isWalking = true
	def onFinishedWalking = _isWalking = false

	def isWalking: Boolean = _isWalking

	/**
	 * used a lot, anoying to do
	 * @return
	 */
	def getDirectionTile: Tile = {
		var result = new Tile
		getFacing match {
			case North => result -= Tile(0,1)
			case South => result += Tile(0,1)
			case East => result += Tile(1,0)
			case West => result -= Tile(1,0)
		}
		return result
	}
}