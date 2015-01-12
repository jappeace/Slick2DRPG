package org.bakkes.game.model.battle;

import org.bakkes.game.model.battle.move.IMove;
import org.bakkes.game.model.pokemon.Pokemon;

public class Turn {
	IMove move;
	Pokemon target;
	Pokemon agressor;
	private int player;
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
	public int getPlayer() {
		return player;
	}
	public void setPlayer(final int player) {
		this.player = player;
	}
}
