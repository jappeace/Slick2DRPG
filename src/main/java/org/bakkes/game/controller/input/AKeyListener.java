package org.bakkes.game.controller.input;

/**
 * mostly only 1 method gets overidden
 *
 * this class makes overiding optional by giving a empty implementation
 */
public class AKeyListener implements IKeyListener{

	@Override
	public void KeyDown(final Key key) {}

	@Override
	public void KeyUp(final Key key) {}

}
