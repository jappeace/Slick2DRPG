package org.bakkes.game.view.components;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

/**
 * containers usaly change existings shapes slightly, by either grouping them together
 * or adding a padding for example
 */
public abstract class AShapeContainer implements IShape{
	private IShape shape;
	/**
	 * this method has to be called
	 * @param shape
	 */
	public AShapeContainer setShape(final IShape shape){
		this.shape = shape;
		return this;
	}
	protected IShape getShape() {
		return shape;
	}

	/**
	 * in the end a container usualy just wants to render the shape
	 */
	@Override
	public void render(final Graphics g) {
		getShape().render(g);
	}
	@Override
	public void setPosition(final Vector2f position) {
		x(position.x);
		y(position.y);
	}

	@Override
	public Vector2f getPosition() {
		return new Vector2f(x(), y());
	}
	@Override
	public void onChangePosition(final Vector2f newLocation) {
	}
}
