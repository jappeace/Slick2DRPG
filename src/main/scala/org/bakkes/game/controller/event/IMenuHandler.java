package org.bakkes.game.controller.event;

import java.util.Collection;

import org.bakkes.game.view.components.IShape;


/**
 * ##IMenuHandler
 *
 * a menu handler is able to provide certain options.
 * if the desired option is select it will be notified with the select method
 */
public interface IMenuHandler {
	public void select(final int item);
	public Collection<IShape> getOptions();
}
