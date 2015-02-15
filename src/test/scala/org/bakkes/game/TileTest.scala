
package org.bakkes.game

import org.bakkes.game.model.map.Direction
import org.bakkes.game.model.map.Tile
import org.newdawn.slick.geom.Vector2f
import org.scalatest.WordSpec
import org.scalatest.prop.TableDrivenPropertyChecks._

class TileTest extends WordSpec {

	"A Tile" when {
		"having these coordinate" should {
			"contain these points" in {
				val tilePoints = Table(
					("Tile", "Points"),
					(new Tile(0, 0), new Vector2f(0.4f, 0.5f)),
					(new Tile(0, 0), new Vector2f(15.9f, 15.99f)),
					(new Tile(0, 0), new Vector2f(0, 0)),
					(new Tile(1, 0), new Vector2f(31.9f, 15.99f)),
					(new Tile(1, 1), new Vector2f(16f, 16f)));
				forAll(tilePoints) { (tile, point) =>
					assert(tile.contains(point))
				}
			}
			"contain not these points" in {
				val tilePoints = Table(
					("Tile", "Points"),
					(new Tile(0, 0), new Vector2f(16f, 16f)),
					(new Tile(1, 1), new Vector2f(15.9f, 15.9f)));
				forAll(tilePoints) { (tile, point) =>
					assert(!tile.contains(point))
				}
			}
			"have not equal hash value" in {
				assert(new Tile(1, 0).hashCode() != new Tile(0, 1).hashCode())
			}
			"have equal hash value" in {
				assert(new Tile(1, 0).hashCode() == new Tile(1, 0).hashCode())
			}
			"indicate the proper direction" in {
				val directionTile = Table(
					("Direction", "Tile"),
					(Direction.North, new Tile(0, -1)),
					(Direction.North, new Tile(1, -3)),
					(Direction.North, new Tile(-1, -3)),
					(Direction.East, new Tile(1, 0)),
					(Direction.East, new Tile(3, 1)),
					(Direction.East, new Tile(3, -1)),
					(Direction.West, new Tile(-1, 0)),
					(Direction.West, new Tile(-3, 1)),
					(Direction.West, new Tile(-3, -1)),
					(Direction.South, new Tile(0, 1)),
					(Direction.South, new Tile(1, 3)),
					(Direction.South, new Tile(-1, 3)))
				forAll(directionTile) { (direction, tile) =>
					assert(tile.getDirection() == direction)
				}
			}
		}
	}
}
