package org.bakkes.game.battle;

import org.bakkes.game.model.pokemon.IMove;
import org.bakkes.game.model.pokemon.IPokemonStatistics;
import org.bakkes.game.model.pokemon.Pokemon;

/**
 * decides turns and wat not
 */
public class BattleMaster implements Runnable{
	IContestent[] contestents = new IContestent[2];

	int currentTurn;
	int speed;

	public void setContestent(final int index, final IContestent contestent){
		contestents[index] = contestent;
	}
	@Override
	public void run() {
		speed = 0;
		currentTurn = 0;
		if(getStats(0).getSpeed() < getStats(1).getSpeed()){
			currentTurn = 1;
		}
		while(!isOver()){
			if(!contestents[currentTurn].isReady()){
				continue;
			}
			final Turn turn = contestents[currentTurn].getTurn();
			execute(turn.getMove(), turn.getAgressor(), turn.getTarget());

			speed -= getStats(otherGuy()).getSpeed();
			if(speed < 0){
				speed = -speed;
				currentTurn = otherGuy();
			}
		}
	}
	private int otherGuy(){
		return (currentTurn + 1) % 2;
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
	private void execute(final IMove move, final Pokemon argressor, final Pokemon target){
        move.getMoveExecutor().executeMove(move, argressor, target);
	}
}
