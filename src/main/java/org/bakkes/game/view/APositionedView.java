package org.bakkes.game.view;

import org.newdawn.slick.geom.Vector2f;

public abstract class APositionedView extends AView implements IPositionable {

	private final Vector2f position = new Vector2f();
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
		onChangePosition(position);
	}

	@Override
	public void y(final float to) {
		position.y = to;
		onChangePosition(position);
	}

	protected void onChangePosition(final Vector2f newLocation){}
	@Override
	public void setPosition(final Vector2f position) {
		x(position.x);
		y(position.y);
	}

	@Override
	public Vector2f getPosition() {
		return position;
	}
}
