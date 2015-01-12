package org.bakkes.game.model.battle.move;

import org.bakkes.game.model.pokemon.Pokemon;

/**
 * sleep poison etc
 */
public interface IEffect {
	/**
	 * to render it
	 * @return
	 */
	String getName();

	void execute(Pokemon pokemon);

	/**
	 * needs to be removed?
	 * sleep wears of after a while
	 * @return
	 */
	boolean isValid();

	/**
	 * effects like paralises don't always work
	 * @return
	 */
	boolean hasSucceeded();
}
