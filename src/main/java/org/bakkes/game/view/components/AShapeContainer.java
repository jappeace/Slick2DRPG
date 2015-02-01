package org.bakkes.game.view.components;

import org.newdawn.slick.Graphics;

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
	public void setShape(final IShape shape){
		this.shape = shape;
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
}
