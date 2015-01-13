package org.bakkes.game.battle;

import java.util.List;

import org.bakkes.game.battle.contestent.IContestent;
import org.bakkes.game.model.battle.Turn;
import org.bakkes.game.model.pokemon.IPokemonStatistics;

import com.google.inject.Inject;

/**
 * decides turns and wat not
 */
public class BattleMaster implements Runnable{
	@Inject private IContestent[] contestents;

	private int contIndex;
	private int speed;
	@Inject private List<Turn> battleLog;

	@Override
	public void run() {
		speed = 0;
		contIndex = 0;
		getBattleLog().clear();
		if(getStats(0).getSpeed() < getStats(1).getSpeed()){
			contIndex = 1;
		}
		while(!isOver()){
			if(!contestents[contIndex].isReady()){
				continue;
			}
			final Turn turn = contestents[contIndex].getTurn();
			getBattleLog().add(turn);
			turn.execute();

			speed -= getStats(otherGuy()).getSpeed();
			if(speed < 0){
				speed = -speed;
				contIndex = otherGuy();
			}
		}
	}
	private int otherGuy(){
		return (contIndex + 1) % 2;
	}

	public boolean isOver(){
		if(getStats(0).getHealth() < 0){
			return true;
		}
		if(getStats(1).getHealth() < 0){
			return true;
		}
		return false;
	}
	private IPokemonStatistics getStats(final int who){
		return contestents[who].getPokemon().getCurrentStats();
	}
	public synchronized List<Turn> getBattleLog() {
		return battleLog;
	}
}
