package org.bakkes.game.controller.battle.contestent;

import org.bakkes.game.model.GameInfo;
import org.bakkes.game.model.battle.Turn;
import org.bakkes.game.model.battle.move.IMove;
import org.bakkes.game.model.pokemon.IPokemonStatistics;
import org.bakkes.game.model.pokemon.Pokemon;
import org.newdawn.slick.util.Log;

public abstract class AContestent implements IContestent{


	private boolean hasWon = false;

	@Override
	public Turn getTurn() {
		final Turn result = new Turn();
		result.setTarget(getTarget());
		result.setAgressor(getOwn());
		result.setMove(getMove());
		return result;
	}

	protected abstract IMove getMove();
	protected abstract Pokemon getOwn();
	protected abstract Pokemon getTarget();

	@Override
	public Pokemon getPokemon() {
		return getOwn();
	}
	/**
	 * so any pokemon will get xp, if a player is stupid and keeps killing himself against another trainer,
	 * the other trainer keeps becoming stronger, which is reasonable.
	 */
	@Override
	public synchronized void onWin() {
		float xp = (getOwn().getLevel() * GameInfo.XP_MODIFIER / getOwn().getSpecies().getTrainingSpeed());
        // even the losers win,
        // IRL you probably learn more by losing
        // however we want to promote winning, so divide by 2
        getTarget().addExperiance((int)xp/2);

		xp = (getTarget().getLevel() * GameInfo.XP_MODIFIER / getTarget().getSpecies().getTrainingSpeed());
        final IPokemonStatistics stats = getOwn().addExperiance((int) xp);
        if(stats != null){
            Log.info("level up: " + stats);
        }
        hasWon = true;
	}
	@Override
	public synchronized boolean hasWon() {
		return hasWon;
	}
}
