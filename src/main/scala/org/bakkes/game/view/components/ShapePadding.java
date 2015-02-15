package org.bakkes.game.view.components;

import org.newdawn.slick.geom.Vector2f;

public class ShapePadding extends AShapeContainer{

	private Vector2f padding;
	@Override
	public float width() {
		return getShape().width() + padding.x;
	}

	@Override
	public float height() {
		return getShape().height() + padding.y;
	}

	@Override
	public float x() {
		return getShape().x();
	}

	@Override
	public float y() {
		return getShape().y();
	}

	@Override
	public void x(final float to) {
		getShape().x(to + padding.x);
	}

	@Override
	public void y(final float to) {
		getShape().y(to + padding.y);
	}

	public ShapePadding setPadding(float padding) {
		return setPadding(new Vector2f(padding,padding));
	}
	public ShapePadding setPadding(final Vector2f padding) {
		this.padding = padding;
		return this;
	}

}
