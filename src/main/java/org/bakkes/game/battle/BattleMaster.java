package org.bakkes.game.battle;

import java.util.LinkedList;
import java.util.List;

import org.bakkes.game.model.battle.Turn;
import org.bakkes.game.model.battle.move.IMove;
import org.bakkes.game.model.pokemon.IPokemonStatistics;
import org.bakkes.game.model.pokemon.Pokemon;

/**
 * decides turns and wat not
 */
public class BattleMaster implements Runnable{
	private IContestent[] contestents = new IContestent[2];

	private int contIndex;
	private int speed;
	private List<Turn> battleLog = new LinkedList<>();

	public void setContestent(final int index, final IContestent contestent){
		contestents[index] = contestent;
	}
	@Override
	public void run() {
		speed = 0;
		contIndex = 0;
		battleLog.clear();
		if(getStats(0).getSpeed() < getStats(1).getSpeed()){
			contIndex = 1;
		}
		while(!isOver()){
			if(!contestents[contIndex].isReady()){
				continue;
			}
			final Turn turn = contestents[contIndex].getTurn();
			turn.setPlayer(contIndex);
			battleLog.add(turn);
			executeMove(turn.getMove(), turn.getAgressor(), turn.getTarget());

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
	private void executeMove(final IMove move, final Pokemon argressor, final Pokemon target){
		argressor.damage(move.getDamage());
	}
}
