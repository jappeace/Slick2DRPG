package org.bakkes.game.controller.scripting;

import org.bakkes.game.AModule;
import org.bakkes.game.model.pokemon.Pokemon;

import com.google.inject.Provides;

public class PokemonModule extends AModule{
	private Pokemon pokemon;

	public PokemonModule(final Pokemon which){
		pokemon = which;
	}

	public @Provides Pokemon providePokemon() {
		return pokemon;
	}

	public void setPokemon(final Pokemon pokemon) {
		this.pokemon = pokemon;
	}

}
