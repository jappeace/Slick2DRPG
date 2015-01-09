package org.bakkes.game.battle;

import java.util.ArrayList;
import java.util.Random;

import org.bakkes.game.GameInfo;
import org.bakkes.game.entity.Player;
import org.bakkes.game.scripting.interfaces.IPokemon;
import org.bakkes.game.state.BattleState;
import org.bakkes.game.state.CommonGameState;

public class Battle {
	private Random random = new Random();
	
	private IPokemon enemy;
	private ArrayList<String> battleLog;
	private CommonGameState parentState;
	private boolean isOver = false;
	private boolean playerWon = false;
	
	public Battle(IPokemon enemy) {
		this.enemy = enemy;
		//weaken enemy a bit, randomly
		this.enemy.set_earth_strength(this.enemy.get_earth_strength() - random.nextInt(30));
		this.enemy.set_fire_strength(this.enemy.get_fire_strength() - random.nextInt(30));
		this.enemy.set_water_strength(this.enemy.get_water_strength() - random.nextInt(30));
		//this.getPlayer().getPokemon().initialize();
		//this.getPlayer().getPokemon().info(); //Disable this, player should heal its pokemon with protein
		this.battleLog = new ArrayList<String>();
	}
	
	public void executeMove(IMove m, boolean fromPlayer) {
		IPokemon moveExecutor = getCorrectPokemon(fromPlayer);
		IPokemon executedOn = getCorrectPokemon(!fromPlayer);
		
		int oldHp = executedOn.get_health();
		
		String moveExecutorName = fromPlayer ? "Player" : "Enemy";
		String executedOnName = fromPlayer ? "Enemy" : "Player";
		battleLog.add(moveExecutorName + " used " + m.get_name());
		m.execute(moveExecutor, executedOn);
		battleLog.add(executedOnName + " HP: " + oldHp + " -> " + executedOn.get_health());
		battleLog.add("________");
		if(executedOn.get_health() <= 0) { //someone won
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
	
	public IPokemon getEnemy() {
		return this.enemy;
	}
	
	public Player getPlayer() {
		return GameInfo.getInstance().player;
	}
	
	public IPokemon getCorrectPokemon(boolean isPlayer) {
		return isPlayer ? getPlayer().getPokemon() : enemy;
	}
	
	public boolean isOver() {
		return isOver;
	}
	
	public boolean hasPlayerWon() {
		return isOver && playerWon;
	}
	
}
