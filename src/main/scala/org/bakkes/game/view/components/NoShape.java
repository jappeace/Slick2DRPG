package org.bakkes.game.view.components;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

import com.google.inject.Singleton;

/**
 * use this as init shape to avoid nullpointer exceptions
 *
 * has no functionality other than not wanting to do null checks
 */
@Singleton // save some memory
public class NoShape implements IShape{

	@Override
	public float width() {
		return 0;
	}

	@Override
	public float height() {
		return 0;
	}

	@Override
	public float x() {
		return 0;
	}

	@Override
	public float y() {
		return 0;
	}

	@Override
	public void render(final Graphics g) {}
	@Override
	public void x(final float to) {}
	@Override
	public void y(final float to) {}

	@Override
	public void setPosition(final Vector2f position) {
	}

	@Override
	public Vector2f getPosition() {
		return new Vector2f();
	}
}
