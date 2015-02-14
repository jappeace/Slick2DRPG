package org.bakkes.game.model.pokemon;

import java.util.List;

import org.bakkes.game.model.battle.move.IMove;



/**
 * descibes how a certain kind of pokemon evolves over time
 */
public interface IPokemonSpecies{
	/**
	 * sprites can be named weirdly
	 * @return
	 */
	String getSpriteName();
	String getEvolution();
	String getName();
	/**
	 * when do you become cooler
	 * @return
	 */
	int getEvolutionLevel();
	/**
	 * at level 0, what is are your statistics like health
	 * @return
	 */
	IPokemonStatistics getBase();
	/**
	 * how much possibility do your stats have of increasing per level
	 * @return
	 */
	IPokemonStatistics getIncrease();

	Type getType();
	/**
	 * the 'natural' moves of a pokemon
	 * @return
	 */
	List<IMove> getMoves();

	/**
	 * tougher or rarer pokemon should be harder to train, ie dragonite should be a bitch to train
	 * compared to ratata
	 *
	 * punishes players who like to have the best things, They now have to work hard for it
	 *
	 * a higher number means that a pokemon is easier to level up
	 * @return
	 */
	float getTrainingSpeed();
}
