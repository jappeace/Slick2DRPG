package org.bakkes.game.controller.state.battle;

import java.util.List;

import org.bakkes.game.controller.state.battle.contestent.IContestent;
import org.bakkes.game.model.battle.BattleLogEvent;
import org.bakkes.game.model.battle.Turn;
import org.bakkes.game.model.pokemon.IPokemonStatistics;
import org.newdawn.slick.util.Log;

import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * decides turns and wat not
 */
public class Battle implements Runnable{
	@Inject private IContestent[] contestents;

	private int contIndex;
	private int speed;
	private @Inject List<BattleLogEvent> battleLog;
	private @Inject Provider<BattleLogEvent> logProvider;

	@Override
	public void run() {
		speed = 0;
		contIndex = 0;
		battleLog.clear();
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

			final BattleLogEvent event = logProvider.get();
			event.setTurn(turn);
			battleLog.add(event);

			Log.info("executing " + contestents[contIndex].getClass().getSimpleName() + " his turn");
			turn.execute();

			if(isOver()){
				Log.info("it is al ogre now");
				// perserve the current contestants' turn to detrmin winner
				break;
			}
			event.isExtraTurn = true;
			speed -= getStats(otherGuy()).getSpeed();
			if(speed < 0){
                event.isExtraTurn = false;
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
		return contestents[who].getOwnPokemon().getCurrentStats();
	}
}
