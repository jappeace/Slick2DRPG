package org.bakkes.game.controller.state.battle.contestent;

import org.bakkes.game.model.GameInfo;
import org.bakkes.game.model.battle.Turn;
import org.bakkes.game.model.battle.move.IMove;
import org.bakkes.game.model.pokemon.IPokemonStatistics;
import org.bakkes.game.model.pokemon.Pokemon;
import org.newdawn.slick.util.Log;

public abstract class AContestent implements IContestent{


	private Pokemon ownPokemon;
	private Pokemon targetPokemon;
	private boolean hasWon = false;

	@Override
	public Turn getTurn() {
		final Turn result = new Turn();
		result.setTarget(getTargetPokemon());
		result.setAgressor(getOwnPokemon());
		result.setMove(getMove());
		return result;
	}

	@Override
	public final Pokemon getOwnPokemon() {
		return ownPokemon;
	}

	@Override
	public final Pokemon getTargetPokemon() {
		return targetPokemon;
	}

	@Override
	public final void setOwnPokemon(final Pokemon ownPokemon) {
		this.ownPokemon = ownPokemon;
	}

	@Override
	public final void setTargetPokemon(final Pokemon targetPokemon) {
		this.targetPokemon = targetPokemon;
	}

	protected abstract IMove getMove();

	/**
	 * so any pokemon will get xp, if a player is stupid and keeps killing himself against another trainer,
	 * the other trainer keeps becoming stronger, which is reasonable.
	 */
	@Override
	public synchronized void onWin() {
		final Pokemon own = getOwnPokemon();
		final Pokemon target = getTargetPokemon();

		float xp = (own.getLevel() * GameInfo.XP_MODIFIER / own.getSpecies().getTrainingSpeed());

        // even the losers win,
        // IRL you probably learn more by losing
        // however we want to promote winning, so divide by 2
        target.addExperiance((int)xp/2);

		xp = (target.getLevel() * GameInfo.XP_MODIFIER / target.getSpecies().getTrainingSpeed());
        final IPokemonStatistics stats = own.addExperiance((int) xp);
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
