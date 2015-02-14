package org.bakkes.game.model.map;

public enum Direction {
	/*
	 * the weird values happen to coincide with a sprite sheet in player.dsl
	 * this allowed me to loop trough it
	 */
	/**
	 * up
	 */
	North,
	/**
	 * bottom
	 */
	South,
	/**
	 * left
	 */
	West,
	/**
	 * right
	 */
	East
}
