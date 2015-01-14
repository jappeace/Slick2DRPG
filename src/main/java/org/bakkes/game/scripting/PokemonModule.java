package org.bakkes.game.scripting;

import org.bakkes.game.model.pokemon.Pokemon;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

public class PokemonModule extends AbstractModule{
	private Pokemon pokemon;

	public PokemonModule(final Pokemon which){
		pokemon = which;
	}

	@Override
	protected void configure() {
	}

	public @Provides Pokemon providePokemon() {
		return pokemon;
	}

	public void setPokemon(final Pokemon pokemon) {
		this.pokemon = pokemon;
	}

}
