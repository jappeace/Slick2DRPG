package org.bakkes.game.battle;

import org.bakkes.game.model.pokemon.IMove;
import org.bakkes.game.model.pokemon.Pokemon;

public class Turn {
	IMove move;
	Pokemon target;
	Pokemon agressor;
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
}
