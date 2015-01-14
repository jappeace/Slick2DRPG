package org.bakkes.game.controller.battle.contestent;

import org.bakkes.game.model.GameInfo;
import org.bakkes.game.model.battle.Turn;
import org.bakkes.game.model.battle.move.IMove;
import org.bakkes.game.model.pokemon.IPokemonStatistics;
import org.bakkes.game.model.pokemon.Pokemon;
import org.newdawn.slick.util.Log;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public abstract class AContestent implements IContestent{

	@Inject @Named("own") protected Pokemon ownPokemon;
	@Inject @Named("target") private Pokemon targetPokemon;

	private boolean hasWon = false;

	@Override
	public Turn getTurn() {
		final Turn result = new Turn();
		result.setTarget(targetPokemon);
		result.setAgressor(ownPokemon);
		result.setMove(getMove());
		return result;
	}

	protected abstract IMove getMove();

	@Override
	public Pokemon getPokemon() {
		return ownPokemon;
	}
	/**
	 * so any pokemon will get xp, if a player is stupid and keeps killing himself against another trainer,
	 * the other trainer keeps becoming stronger, which is reasonable.
	 */
	@Override
	public synchronized void onWin() {
		float xp = (ownPokemon.getLevel() * GameInfo.XP_MODIFIER / ownPokemon.getSpecies().getTrainingSpeed());
        // even the losers win,
        // IRL you probably learn more by losing
        // however we want to promote winning, so divide by 2
        targetPokemon.addExperiance((int)xp/2);

		xp = (targetPokemon.getLevel() * GameInfo.XP_MODIFIER / targetPokemon.getSpecies().getTrainingSpeed());
        final IPokemonStatistics stats = ownPokemon.addExperiance((int) xp);
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
