package org.bakkes.game.controller.state.battle.contestent;

import org.bakkes.game.model.pokemon.Pokemon;

import com.google.inject.Inject;
import com.google.inject.name.Named;


public abstract class AI extends AContestent{


	@Inject @Named("target") // flipped because otherwise the player and AI would battle the same guy
	private Pokemon ownPokemon;
	@Inject @Named("own")
	private Pokemon targetPokemon;
	@Override
	public boolean isReady() {
		return true;
	}
	@Override
	protected Pokemon getOwn() {
		return ownPokemon;
	}
	@Override
	protected Pokemon getTarget() {
		return targetPokemon;
	}

}
