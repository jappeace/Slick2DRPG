package org.bakkes.game.controller.state.overworld.command

import com.google.inject.Inject
import org.bakkes.game.model.entity.Character
import org.bakkes.game.model.map.Direction
import org.bakkes.game.model.map.Tile
import org.newdawn.slick.geom.Vector2f
import org.newdawn.slick.util.pathfinding.Path
import org.newdawn.slick.util.pathfinding.PathFinder

class WalkPath @Inject() (
	 val entity: Character,
	 val pathFinder: PathFinder
) extends ACommand {
	private var path: Path = null
	private var currentStep: Int = 0
	private var firstTime: Boolean = true
	private var destination: Tile = null
	private final val added: Vector2f = new Vector2f

	def execute(tpf: Float) {
		if (firstTime) {
			firstTime = false
			path = pathFinder.findPath(null, entity.getTile.left, entity.getTile.top, getDestination.left, getDestination.top)
			if (path == null) {
				done()
				return
			}
		}
		val destinationTile = new Tile(path.getStep(currentStep))
		val diffTile = destinationTile - entity.getTile
		val delta = new Vector2f(diffTile.left*tpf, diffTile.top*tpf)
		val position = entity.getPosition
		if (delta == new Vector2f(0, 0)) {
			if (destinationTile.topLeftPixels.x < position.x) {
				delta.x = -tpf
			}
			if (destinationTile.topLeftPixels.y < position.y) {
				delta.y = -tpf
			}
		}
		var arrived: Boolean = false
		if (Math.abs(added.x + delta.x) >= Tile.WIDTH) {
			val smoothDistance: Float = Tile.WIDTH - Math.abs(added.x) + 0.01f
			delta.x = if (delta.x >= 0) smoothDistance else -smoothDistance
			arrived = true
		}
		if (Math.abs(added.y + delta.y) >= Tile.HEIGHT) {
			val smoothDistance: Float = Tile.HEIGHT - Math.abs(added.y) + 0.01f
			delta.y = if (delta.y >= 0) smoothDistance else -smoothDistance
			arrived = true
		}
		added.add(delta)
		position.add(delta)
		if (delta.x < 0 || delta.y < 0) {
			if (!arrived) {
				return
			}
		}
		if (!destinationTile.contains(position)) {
			return
		}
		entity.setPosition(Tile.PixelsToGridPixels(position))
		currentStep += 1
		added.x = 0
		added.y = 0
		if (currentStep >= path.getLength || isInterupted) {
			done()
			currentStep = 0
			entity.onFinishedWalking
			return
		}
		val nextTile: Tile = new Tile(path.getStep(currentStep))
		entity.onEnterNewTile
		val p: Tile = new Tile(path.getStep(currentStep - 1)).minus(nextTile)
		if (p.left == -1) {
			entity.setFacing(Direction.East)
		}
		else if (p.left == 1) {
			entity.setFacing(Direction.West)
		}
		if (p.top == -1) {
			entity.setFacing(Direction.South)
		}
		else if (p.top == 1) {
			entity.setFacing(Direction.North)
		}
	}

	private def getDestination: Tile = {
		return destination
	}

	def setDestination(destination: Tile) {
		this.destination = destination
	}
}