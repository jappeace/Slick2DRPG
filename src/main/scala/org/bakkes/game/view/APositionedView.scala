package org.bakkes.game.view;

import org.bakkes.game.model.TPosition;
import org.newdawn.slick.geom.Vector2f;

abstract class APositionedView() extends AView with TPosition {
	private val position:Vector2f  = new Vector2f()
	override def getPosition(): Vector2f = position
}
