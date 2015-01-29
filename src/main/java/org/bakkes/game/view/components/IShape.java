package org.bakkes.game.view.components;


/**
 * #IShape
 * A very consize contract of things drawable on the screen.
 * I use it more to let eclipse generate shorter getter and setters (add unimplemented methods)
 * the code becomes arguably more readable by removing the get and set
 *
 * I am to lazy for all the getting and setting
 */
public interface IShape {
	public float width();
	public float height();
	public float x();
	public float y();
	public void x(float to);
	public void y(float to);
}
