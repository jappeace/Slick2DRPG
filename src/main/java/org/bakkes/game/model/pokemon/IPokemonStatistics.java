package org.bakkes.game.model.pokemon;

import java.util.Random;

import nl.jappieklooster.groovy.math.IMinusOperator;
import nl.jappieklooster.groovy.math.IPlusOperator;

public interface IPokemonStatistics extends IPlusOperator<IPokemonStatistics>, IMinusOperator<IPokemonStatistics> {

	public abstract int getHealth();

	public abstract int getAttack();

	public abstract int getDefence();

	public abstract int getSpeed();

	/**
	 * create a new pokemonstatistics based upon the random provided.
	 * All atributes will be randomly lower or equal to current stats.
	 * @param random
	 * @return
	 */
	IPokemonStatistics createFrom(Random random);

}