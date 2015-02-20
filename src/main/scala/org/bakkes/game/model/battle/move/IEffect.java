package org.bakkes.game.model.battle.move;

/**
 * an effect is an move but last for multiple turns
 */
public interface IEffect extends IMove{

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
