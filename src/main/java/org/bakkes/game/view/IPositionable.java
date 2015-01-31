package org.bakkes.game.view;

/**
 * easy function calls to (probably) a vector backend
 */
public interface IPositionable {
	public float x();
	public float y();
	public void x(float to);
	public void y(float to);
}
