package org.bakkes.game.battle;

import java.util.LinkedList;
import java.util.Random;

import org.bakkes.game.model.pokemon.IMove;
import org.bakkes.game.model.pokemon.IPokemonStatistics;
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

	int excesSpeed;
	boolean skipEnemyTurn = false;

	public Battle(final Pokemon player, final Pokemon enemy) {
		this.enemy = enemy;
		this.player = player;
		this.battleLog = new LinkedList<>();
	}

	public void executeMove(final IMove m, final boolean isPlayer) {
		if(isPlayer){
			final IPokemonStatistics plrStats = player.getCurrentStats();
			final IPokemonStatistics nmyStats = enemy.getCurrentStats();
			excesSpeed += plrStats.getSpeed() - nmyStats.getSpeed();
			if(excesSpeed > plrStats.getSpeed()){
				battleLog.add("your " + player.getName() + " has managed to get a extra turn due to his speed");
				excesSpeed -= nmyStats.getSpeed();

				skipEnemyTurn = true;
			}
			if(excesSpeed < nmyStats.getSpeed()){
				excesSpeed += plrStats.getSpeed();
				battleLog.add("due to his feriocues speed, " + enemy.getName() + " can do a move without being interupted by " + player.getName());
				return;
			}
		}else if(skipEnemyTurn){
            skipEnemyTurn = true;
			return;
		}
		final Pokemon moveExecutor = getCorrectPokemon(isPlayer);
		final Pokemon executedOn = getCorrectPokemon(!isPlayer);

		final int oldHp = executedOn.getCurrentStats().getHealth();

		final String moveExecutorName = isPlayer ? "Player" : "Enemy";
		final String executedOnName = isPlayer ? "Enemy" : "Player";
		battleLog.add(moveExecutorName + " used " + m.getName());


		executedOn.damage(m.getDamage()); // so creative
		battleLog.add(executedOnName + " HP: " + oldHp + " -> " + executedOn.getCurrentStats().getHealth());
		battleLog.add("________");
		if(!executedOn.isAlive()) { //someone won
			playerWon = isPlayer;
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

	public Pokemon getPlayer() {
		return player;
	}

	public Pokemon getCorrectPokemon(final boolean isPlayer) {
		return isPlayer ? getPlayer() : enemy;
	}

	public boolean isOver() {
		return isOver;
	}

	public boolean hasPlayerWon() {
		return isOver && playerWon;
	}

}
