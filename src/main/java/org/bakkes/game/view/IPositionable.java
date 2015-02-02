package org.bakkes.game.view;

import org.newdawn.slick.geom.Vector2f;

/**
 * easy function calls to (probably) a vector backend
 */
public interface IPositionable {
	public float x();
	public float y();
	public void x(float to);
	public void y(float to);
    public void setPosition(final Vector2f position);
    public Vector2f getPosition();
}
