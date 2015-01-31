package org.bakkes.game.view.components;

import org.bakkes.game.view.IPositionable;
import org.bakkes.game.view.IRenderable;


/**
 * #IShape
 * A very consize contract of things drawable on the screen.
 * I use it more to let eclipse generate shorter getter and setters (add unimplemented methods)
 * the code becomes arguably more readable by removing the get and set
 *
 * I am to lazy for all the getting and setting
 */
public interface IShape extends IPositionable, IRenderable{
	public float width();
	public float height();
}
