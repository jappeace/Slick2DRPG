package org.bakkes.game.view.components;

import org.newdawn.slick.geom.Vector2f;

public abstract class AShape implements IShape{

    private Vector2f position = new Vector2f();

	@Override
	public float x() {
		return position.x;
	}
	@Override
	public float y() {
		return position.y;
	}
	@Override
	public void x(final float to) {
		position.x = to;
	}
	@Override
	public void y(final float to) {
		position.y = to;
	}
    public void setPosition(final Vector2f position) {
		this.position = position.copy();
	}
}
