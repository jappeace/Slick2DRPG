package org.bakkes.game.model.battle;

public class BattleLogEvent {

	private Turn turn;
	public boolean isExtraTurn;

	public Turn getTurn() {
		return turn;
	}

	public void setTurn(final Turn turn) {
		this.turn = turn;
	}

}
