package org.bakkes.game.model.map

import nl.jappieklooster.scala.{TMultiply, TMinus, TPlus}
import org.newdawn.slick.geom.{Rectangle, Shape, Vector2f}
import org.newdawn.slick.util.pathfinding.Path

case class Tile(left:Int, top:Int) extends TMinus[Tile] with TPlus[Tile] with TMultiply[Tile]{
	def this() {
		this(0, 0)
	}

	def this(source: Tile) {
		this(source.left, source.top)
	}

	def this(source: Path#Step) {
		this(source.getX, source.getY)
	}

	def this(coordinates: Vector2f) {
		this(coordinates.x.toInt, coordinates.y.toInt)
	}

	def toShape: Shape = {
		val coord: Vector2f = topLeftPixels
		return new Rectangle(coord.x, coord.y, Tile.WIDTH, Tile.HEIGHT)
	}

	def toVector: Vector2f = new Vector2f(top, left)
	def topLeftPixels: Vector2f = new Vector2f(left * Tile.WIDTH, top * Tile.HEIGHT)

	def bottomRightPixels: Vector2f = new Vector2f((left + 1) * Tile.WIDTH, (top + 1) * Tile.HEIGHT)

	def contains(pixels: Vector2f): Boolean = {
		val tl: Vector2f = topLeftPixels
		if (tl.x > pixels.x) {
			return false
		}
		if (tl.y > pixels.y) {
			return false
		}
		val br: Vector2f = bottomRightPixels
		if (br.x <= pixels.x) {
			return false
		}
		if (br.y <= pixels.y) {
			return false
		}
		return true
	}

	def minus(tile: Tile): Tile =  new Tile(
		this.left - tile.left,
		this.top - tile.top
	)

	def plus(tile: Tile): Tile = new Tile(
		this.left + tile.left,
		this.top + tile.top
	)
	override def toString: String = {
		return "Tile(" + left + "," + top + ")"
	}

	override def multiply(righthandSide: Tile): Tile = new Tile(
		this.left * righthandSide.left,
		this.top * righthandSide.top
	)

	/* wtf
	def multiply(righthandSide: Vector2f): Vector2f = {
		return new Vector2f(left * righthandSide.x, top * righthandSide.y)
	}*/

	def getDirection: Direction = {
		val tl: Vector2f = this.topLeftPixels
		val degrees: Double = tl.getTheta
		if (degrees <= 135 && degrees > 45) {
			return Direction.South
		}
		if (degrees <= 225 && degrees > 135) {
			return Direction.West
		}
		if (degrees <= 315 && degrees > 225) {
			return Direction.North
		}
		return Direction.East
	}

}
object Tile {
	val WIDTH = 16
	val HEIGHT = 16

	def createFromPixelsCoordinates(pixelCoordinates: Vector2f): Tile = {
		val result: Tile = new Tile(
			(pixelCoordinates.x - (pixelCoordinates.x % Tile.WIDTH)).toInt / Tile.WIDTH,
			(pixelCoordinates.getY - (pixelCoordinates.getY % Tile.HEIGHT)).toInt / Tile.HEIGHT
		)
		return result
	}

	/**
	 *
	 * @param p
	 * @return
	 */
	def PixelsToGridPixels(p: Vector2f): Vector2f = {
		return new Vector2f((p.getX - (p.getX % Tile.WIDTH)), (p.getY - (p.getY % Tile.HEIGHT)))
	}
}
