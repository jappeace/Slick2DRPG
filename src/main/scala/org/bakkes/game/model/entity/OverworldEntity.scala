package org.bakkes.game.model.entity

import org.bakkes.game.model.map.Tile
import org.bakkes.game.model.{ASpriteNamedModel, APosition}

/**
 * a entity in the overworld has to have a position (form top left of the map in pixels),
 * and offers
 */
abstract class OverworldEntity extends ASpriteNamedModel with IOverworldEntity with APosition with TInteractor {
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

}