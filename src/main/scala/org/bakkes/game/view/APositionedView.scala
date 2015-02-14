package org.bakkes.game.view;

import org.bakkes.game.model.Position;
import org.newdawn.slick.geom.Vector2f;

abstract class APositionedView extends AView with Position {
	private val position:Vector2f  = new Vector2f()
	override def getPosition(): Vector2f = position
}
