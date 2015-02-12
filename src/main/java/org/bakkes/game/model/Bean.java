package org.bakkes.game.model;

import nl.jappieklooster.annotation.Nullable;

/**
 * encapsulate a object with this to pass it arround to multiple objects
 * make sure to mark it as a singleton in the proper module, can't do it here
 * cause for java it means that all beans will become the singleton
 *
 * it is not really a model in that it is just a lack of pointers in Java
 * but this lack of pointers also make java easier to use... for newbies...
 */
public class Bean< T> {

	private @Nullable T data;

	public synchronized @Nullable T getData() {
		return data;
	}

	public synchronized void setData(final @Nullable T data) {
		this.data = data;
	}
}