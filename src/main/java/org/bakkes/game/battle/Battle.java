package org.bakkes.game.battle;

import java.util.List;

import org.bakkes.game.battle.contestent.IContestent;
import org.bakkes.game.model.battle.Turn;
import org.bakkes.game.model.pokemon.IPokemonStatistics;
import org.newdawn.slick.util.Log;

import com.google.inject.Inject;

/**
 * decides turns and wat not
 */
public class Battle implements Runnable{
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
		while(true){
			if(!contestents[contIndex].isReady()){
				try {
					Thread.sleep(10);
				} catch (final InterruptedException e) {
					Log.warn("interupt");
				}
				continue;
			}
			final Turn turn = contestents[contIndex].getTurn();
			getBattleLog().add(turn);
			Log.info("executing " + contestents[contIndex].getClass().getSimpleName() + " his turn");
			turn.execute();

			if(isOver()){
				Log.info("it is al ogre now");
				// perserve the current contestants' turn to detrmin winner
				break;
			}
			speed -= getStats(otherGuy()).getSpeed();
			if(speed < 0){
				speed = -speed;
				contIndex = otherGuy();
			}
		}
		contestents[contIndex].onWin();
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
