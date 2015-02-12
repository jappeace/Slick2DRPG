package org.bakkes.game.controller.event.input;

public interface IKeyListener {
	default public void KeyDown(final Key key){}
	default public void KeyUp(final Key key){}
}
