package org.bakkes.game.model.pokemon;

import org.bakkes.game.battle.IMoveExecutor;


public interface IMove {
	String getName();
	int getDamage();
	IMoveExecutor getMoveExecutor();
}
