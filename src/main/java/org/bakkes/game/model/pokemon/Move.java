package org.bakkes.game.model.pokemon;

import org.bakkes.game.battle.IMoveExecutor;
import org.bakkes.game.battle.move.SimpleDamage;

public class Move implements IMove{

	String name;
	int damage;
	IMoveExecutor executor = new SimpleDamage();
	@Override
	public final String getName() {
		return name;
	}
	@Override
	public final int getDamage() {
		return damage;
	}
	public final void setName(final String name) {
		this.name = name;
	}
	public final void setDamage(final int damage) {
		this.damage = damage;
	}
	public final void setMoveExecutor(final IMoveExecutor executor){
		this.executor = executor;
	}
	@Override
	public IMoveExecutor getMoveExecutor() {
		return executor;
	}

}
