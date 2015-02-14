package org.bakkes.game.view.components;

import org.bakkes.game.model.Position;
import org.newdawn.slick.geom.Vector2f;

abstract class APositionable extends Position {
	private val position:Vector2f  = new Vector2f()
	override def getPosition(): Vector2f = position
}
