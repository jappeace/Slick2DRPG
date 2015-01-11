package org.bakkes.game.model.pokemon;

public class Move implements IMove{

	String name;
	int damage;
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

}
