package org.bakkes.game.controller.battle.contestent;

import org.bakkes.game.model.battle.Turn;
import org.bakkes.game.model.pokemon.Pokemon;


public interface IContestent {

	boolean isReady();
	Turn getTurn();
	Pokemon getPokemon();
	void onWin();
	boolean hasWon();
}
