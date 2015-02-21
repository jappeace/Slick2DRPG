package org.bakkes.game.model.pokemon;

import nl.jappieklooster.groovy.operator.IMinusOperator;
import nl.jappieklooster.groovy.operator.IPlusOperator;

public interface IPokemonStatistics extends IPlusOperator<IPokemonStatistics>, IMinusOperator<IPokemonStatistics> {

	public abstract int getHealth();

	public abstract int getAttack();

	public abstract int getDefence();

	public abstract int getSpeed();
}