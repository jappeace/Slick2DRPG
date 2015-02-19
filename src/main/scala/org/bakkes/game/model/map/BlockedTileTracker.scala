package org.bakkes.game.model.map

import com.google.inject.Singleton
import collection.JavaConversions._
/**
 * keeps track of blocked tiles that change dynamicly.
 * does not keep track of the staticly blocked tiles defined in the .tmx files
 */
@Singleton class BlockedTileTracker {
	/**
	 * TODO: replace string with enum of layers
	 */
	private var blockers = Map[String, Iterable[_ <: IBlocksTiles]]()

	def putBlockedTiles(layerName: String, tiles: java.util.Collection[_ <: IBlocksTiles]) {
		blockers += (layerName -> tiles)
	}

	def findAllBlockedTiles: java.lang.Iterable[Tile] = {
		// wtf, scala your insane
		val result = blockers.values // get the map values
				.map(
				blockList => blockList match {
					case r: Iterable[IBlocksTiles] => r
					case _ => throw new RuntimeException("this is impossible")
				}
				)
				.flatten // merge them into 1 collection type Iterable[IBlockedTiles]
				.map(
					blocker => blocker.getBlockedTiles // get the result of IBlockedTiles
				) // now we have another collection of collection of tiles
				.flatten // now put all the result together in 1 big collection
				.toSet // filter out duplicates
		return result
	}

}