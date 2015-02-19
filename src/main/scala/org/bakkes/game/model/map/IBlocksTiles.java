package org.bakkes.game.model.map;

import scala.collection.immutable.Seq;

/**
 * allows the blocktile tracke
 */
public interface IBlocksTiles {
	Seq<Tile> getBlockedTiles();
}
