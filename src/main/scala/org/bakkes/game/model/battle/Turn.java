package org.bakkes.game.model.battle;

import org.bakkes.game.model.battle.move.IMove;
import org.bakkes.game.model.pokemon.IPokemonStatistics;
import org.bakkes.game.model.pokemon.Pokemon;
import org.bakkes.game.model.pokemon.PokemonStatistics;

public class Turn {
	IMove move;
	Pokemon target;
	Pokemon agressor;
	private IPokemonStatistics change;
	public final IMove getMove() {
		return move;
	}
	public final Pokemon getTarget() {
		return target;
	}
	public final Pokemon getAgressor() {
		return agressor;
	}
	public final void setMove(final IMove move) {
		this.move = move;
	}
	public final void setTarget(final Pokemon target) {
		this.target = target;
	}
	public final void setAgressor(final Pokemon agressor) {
		this.agressor = agressor;
	}
	public void execute(){
		change = new PokemonStatistics(target.getCurrentStats());
		final int damage = move.getDamage();
		target.damage(damage);
		change = target.getCurrentStats().minus(change);
	}
	public IPokemonStatistics getChange(){
		return change;
	}
}
