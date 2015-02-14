package org.bakkes.game.controller;

public interface IUpdatable {
	/**
	 * @param delta = time per frame, time diference per frame
	 */
	public void update(final int delta);
}
