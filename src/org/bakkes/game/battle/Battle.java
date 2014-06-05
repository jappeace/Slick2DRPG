package org.bakkes.game.battle;

import org.bakkes.game.GameInfo;
import org.bakkes.game.entity.Player;
import org.bakkes.game.scripting.interfaces.IPokemon;

public class Battle {
	private IPokemon enemy;
	
	public Battle(IPokemon enemy) {
		this.enemy = enemy;
	}
	
	public IPokemon getEnemy() {
		return this.enemy;
	}
	public Player getPlayer() {
		return GameInfo.getInstance().player;
	}
	
}
