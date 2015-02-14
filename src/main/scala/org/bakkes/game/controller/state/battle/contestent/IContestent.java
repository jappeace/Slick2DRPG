package org.bakkes.game.controller.state.battle.contestent;

import org.bakkes.game.model.battle.Turn;
import org.bakkes.game.model.pokemon.Pokemon;


public interface IContestent {

	boolean isReady();
	Turn getTurn();
	void onWin();
	boolean hasWon();
	void setOwnPokemon(final Pokemon ownPokemon);
	void setTargetPokemon(final Pokemon targetPokemon);
	Pokemon getOwnPokemon();
	Pokemon getTargetPokemon();
}
