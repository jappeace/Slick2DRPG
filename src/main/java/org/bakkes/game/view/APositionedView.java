package org.bakkes.game.view;

import org.newdawn.slick.geom.Vector2f;

public abstract class APositionedView extends AView implements IPositionable {

	private final Vector2f position = new Vector2f();
	@Override
	public Vector2f getPosition() {
		return position;
	}
}
