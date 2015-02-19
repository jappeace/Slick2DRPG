package org.bakkes.game.controller.event.input

import com.google.inject.Inject
import com.google.inject.Provider
import org.bakkes.game.model.entity.player.Player
import org.bakkes.game.model.map.Tile

/**
 * ## MovementListener
 * allows the main character to be controlled by the keyboard
 */
class MovementListener @Inject() (
	private var player: Player,
	private var tileProvider: Provider[Tile]
) extends AKeyListener with IKeyListener {
	override def KeyDown(key: Key) {
		var diff = tileProvider.get
		if (key.isUp) {
			diff -= Tile(0,1)
		}
		else if (key.isLeft) {
			diff -= Tile(1,0)
		}
		else if (key.isDown) {
			diff += Tile(0,1)
		}
		else if (key.isRight) {
			diff += Tile(1,0)
		}
		player.moveTo(player.getTile.plus(diff))
	}
}