package org.bakkes.game.battle;

import java.util.ArrayList;
import java.util.Random;

import org.bakkes.game.GameInfo;
import org.bakkes.game.model.pokemon.IMove;
import org.bakkes.game.model.pokemon.Pokemon;
import org.bakkes.game.state.CommonGameState;

public class Battle {
	private Random random = new Random();

	private Pokemon enemy;
	private ArrayList<String> battleLog;
	private CommonGameState parentState;
	private boolean isOver = false;
	private boolean playerWon = false;

	public Battle(final Pokemon enemy) {
		this.enemy = enemy;
		this.battleLog = new ArrayList<String>();
	}

	public void executeMove(final IMove m, final boolean fromPlayer) {
		final Pokemon moveExecutor = getCorrectPokemon(fromPlayer);
		final Pokemon executedOn = getCorrectPokemon(!fromPlayer);

		final int oldHp = executedOn.getHealth();

		final String moveExecutorName = fromPlayer ? "Player" : "Enemy";
		final String executedOnName = fromPlayer ? "Enemy" : "Player";
		battleLog.add(moveExecutorName + " used " + m.getName());

		executedOn.setHealth(executedOn.getHealth() - m.getDamage()); // so creative
		battleLog.add(executedOnName + " HP: " + oldHp + " -> " + executedOn.getHealth());
		battleLog.add("________");
		if(executedOn.getHealth() <= 0) { //someone won
			playerWon = fromPlayer;
			battleLog.add(executedOnName + " died!");
			battleLog.add(moveExecutorName + " has won this battle");
			battleLog.add("Press enter to leave!");
			isOver = true;
		}
	}

	public ArrayList<String> getBattleLog() {
		return battleLog;
	}

	public Pokemon getEnemy() {
		return this.enemy;
	}

	public Pokemon getPlayerPokemon() {
		return GameInfo.getInstance().player.getPokemon();
	}

	public Pokemon getCorrectPokemon(final boolean isPlayer) {
		return isPlayer ? getPlayerPokemon() : enemy;
	}

	public boolean isOver() {
		return isOver;
	}

	public boolean hasPlayerWon() {
		return isOver && playerWon;
	}

}
