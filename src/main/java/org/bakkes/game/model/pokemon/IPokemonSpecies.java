package org.bakkes.game.model.pokemon;

import java.util.Collection;


/**
 * descibes how a certain kind of pokemon evolves over time
 */
public interface IPokemonSpecies {
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
	 * at level 0, what is your health
	 * @return
	 */
	int getBaseHealth();
	int getBaseAttack();
	int getBaseDefence();
	int getBaseSpeed();
	/**
	 * how much you're health can increase per level
	 * @return
	 */
	int getIncreaseHealth();
	int getIncreaseAttack();
	int getIncreaseDefence();
	int getIncreaseSpeed();
	Type getType();
	/**
	 * the 'natural' moves of a pokemon
	 * @return
	 */
	Collection<String> getMoves();
}
