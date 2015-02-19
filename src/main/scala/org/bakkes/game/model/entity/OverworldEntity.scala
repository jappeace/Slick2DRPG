package org.bakkes.game.model.entity

import groovy.lang.Closure
import org.bakkes.game.model.map.Tile
import org.bakkes.game.model.{ASpriteNamedModel, TPosition}
import org.newdawn.slick.util.Log

/**
 * a entity in the overworld has to have a position (form top left of the map in pixels),
 * and offers
 */
abstract class OverworldEntity extends ASpriteNamedModel with IOverworldEntity with TPosition {
	def setPosition(to: Tile) {
		setPosition(to.topLeftPixels)
	}

	/**
	 * here we sort of assume all entities block 4 tiles
	 * the getTile(), and the one left below and leftbelow of it
	 */
	override def getBlockedTiles = List[Tile](
			getTile,
			getTile.plus(new Tile(1, 0)),
			getTile.plus(new Tile(0, 1)),
			getTile.plus(new Tile(1, 1))
		)

	def getTile: Tile = {
		return Tile.createFromPixelsCoordinates(position)
	}

	private var onInteract: Closure[Void] = null

	override def interact {
		Log.info("interacting with " + getName + " on location: " + getPosition)
		if (onInteract == null) {
			Log.info("no interaction present")
			return
		}
		onInteract.call
	}

	def setInteract(callback: Closure[Void]) {
		onInteract = callback
	}
}