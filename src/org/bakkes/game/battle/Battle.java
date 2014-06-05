package org.bakkes.game.battle;

import java.util.ArrayList;

import org.bakkes.game.GameInfo;
import org.bakkes.game.entity.Player;
import org.bakkes.game.scripting.interfaces.IPokemon;
import org.bakkes.game.state.BattleState;
import org.bakkes.game.state.CommonGameState;

public class Battle {
	private IPokemon enemy;
	private ArrayList<String> battleLog;
	private CommonGameState parentState;
	
	public Battle(CommonGameState parentState, IPokemon enemy) {
		this.enemy = enemy;
		this.battleLog = new ArrayList<String>();
	}
	
	public void executeMove(IMove m, boolean fromPlayer) {
		IPokemon moveExecutor = getCorrectPokemon(fromPlayer);
		IPokemon executedOn = getCorrectPokemon(!fromPlayer);
		
		int oldHp = executedOn.get_health();
		
		String who = fromPlayer ? "Player" : "Enemy";
		String whoNot = fromPlayer ? "Enemy" : "Player";
		battleLog.add(who + " used " + m.get_name());
		m.execute(moveExecutor, executedOn);
		battleLog.add(whoNot + " HP: " + oldHp + " -> " + executedOn.get_health());
		battleLog.add("________");
		if(executedOn.get_health() <= 0) { //someone won
			
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
	
}
