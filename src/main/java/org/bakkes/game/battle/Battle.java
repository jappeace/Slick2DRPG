package org.bakkes.game.battle;

import java.util.LinkedList;
import java.util.Random;

import org.bakkes.game.model.pokemon.IMove;
import org.bakkes.game.model.pokemon.Pokemon;
import org.bakkes.game.state.CommonGameState;

public class Battle {
	private Random random = new Random();

	private Pokemon enemy;
	private LinkedList<String> battleLog;
	private CommonGameState parentState;
	private boolean isOver = false;
	private boolean playerWon = false;
	private Pokemon player;

	public Battle(final Pokemon player, final Pokemon enemy) {
		this.enemy = enemy;
		this.player = player;
		this.battleLog = new LinkedList<>();
	}

	public void executeMove(final IMove m, final boolean fromPlayer) {
		final Pokemon moveExecutor = getCorrectPokemon(fromPlayer);
		final Pokemon executedOn = getCorrectPokemon(!fromPlayer);

		final int oldHp = executedOn.getCurrentStats().getHealth();

		final String moveExecutorName = fromPlayer ? "Player" : "Enemy";
		final String executedOnName = fromPlayer ? "Enemy" : "Player";
		battleLog.add(moveExecutorName + " used " + m.getName());


		executedOn.damage(m.getDamage()); // so creative
		battleLog.add(executedOnName + " HP: " + oldHp + " -> " + executedOn.getCurrentStats().getHealth());
		battleLog.add("________");
		if(!executedOn.isAlive()) { //someone won
			moveExecutor.addExperiance((int)(executedOn.getLevel() * 10 / executedOn.getSpecies().getTrainingSpeed()));
			playerWon = fromPlayer;
			battleLog.add(executedOnName + " died!");
			battleLog.add(moveExecutorName + " has won this battle");
			battleLog.add("Press enter to leave!");
			isOver = true;
		}
	}

	public LinkedList<String> getBattleLog() {
		return battleLog;
	}

	public Pokemon getEnemy() {
		return this.enemy;
	}

	public Pokemon getPlayerPokemon() {
		return player;
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
