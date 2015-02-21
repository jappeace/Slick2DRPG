package org.bakkes.game.model.pokemon;

import org.bakkes.game.model.INamed;
import org.bakkes.game.model.battle.move.IMove;
import scala.Option;
import scala.collection.Seq;


/**
 * descibes how a certain kind of pokemon evolves over time
 */
public interface IPokemonSpecies extends INamed{
	/**
	 * sprites can be named weirdly
	 * @return
	 */
	Option<IPokemonSpecies> getEvolution();
	float getCatchChance();
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
	Seq<IMove> getMoves();

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
