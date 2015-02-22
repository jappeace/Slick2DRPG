package org.bakkes.game.view.components

import com.google.inject.Inject
import org.bakkes.game.model.map.Direction
import org.bakkes.game.view.APositionedView
import org.newdawn.slick.Graphics
import org.newdawn.slick.geom.Vector2f

import scala.beans.BeanProperty

/**
 * ##SpritedNameView
 *
 * puts a sprite of a IHasSpriteName to the name, depending on the direction (default West (left))
 *
 * this is basicly a more concrete implementation of ShapeComposition. It figures out what to do
 * with values retrieved from IHasSpriteName
 *
 * the main purpouse of this class was to be used as menu item
 */
class ShapeWithString @Inject()(
	line: TextLine,
	composition: ShapeComposition,
	padding: ShapePadding
) extends APositionedView with IShape {

	@BeanProperty
	var direction = Direction.West

	def setShape(shape:IShape, text: String) {
		line.setText(text)
		val heightDifference: Float = shape.height - line.height
		padding.setPadding(new Vector2f(0, heightDifference / 2))
		padding.setShape(line)
		composition.setShape(padding)
		composition.put(direction, shape)
	}

	override protected def renderView(g: Graphics) = composition.render(g)

	override def onChangePosition(to: Vector2f) {
		composition.x(to.x)
		composition.y(to.y)
	}

	override def width: Float = composition.width
	override def height: Float = composition.height
}