package org.bakkes.game.controller.state.battle;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.bakkes.game.controller.state.battle.contestent.IContestent;
import org.bakkes.game.model.battle.BattleLogEvent;
import org.bakkes.game.model.battle.Turn;
import org.bakkes.game.model.pokemon.IPokemonStatistics;
import org.newdawn.slick.util.Log;

import java.util.List;

/**
 * decides turns and wat not
 */
public class Battle implements Runnable{
	@Inject private IContestent[] contestents;

	private int contIndex;
	private int speed;
	private @Inject List<BattleLogEvent> battleLog;
	private @Inject Provider<BattleLogEvent> logProvider;
	private boolean isOver;

	@Override
	public void run() {
		speed = 0;
		contIndex = 0;
		battleLog.clear();
		isOver = false;
		if(getStats(0).getSpeed() < getStats(1).getSpeed()){
			contIndex = 1;
		}
		while(!isOver){
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

			if(isOver(turn)){
				isOver = true;
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
		return isOver;
	}
	private boolean isOver(Turn t){
		if(getStats(0).getHealth() < 0){
			return true;
		}
		if(getStats(1).getHealth() < 0){
			return true;
		}
		return t.getMove().isBattleOver();
	}
	private IPokemonStatistics getStats(final int who){
		return contestents[who].getOwnPokemon().getCurrentStats();
	}
}
