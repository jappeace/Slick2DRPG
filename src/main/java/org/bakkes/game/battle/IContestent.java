package org.bakkes.game.battle;

import org.bakkes.game.model.pokemon.Pokemon;


public interface IContestent {

	boolean isReady();
	Turn getTurn();
	Pokemon getPokemon();
}
