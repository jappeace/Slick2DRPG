package org.bakkes.game.scripting;

import org.bakkes.game.R;
import org.bakkes.game.model.pokemon.Pokemon;
import org.bakkes.game.model.pokemon.PokemonSpecies;


public class PokemonManager {

	private static PokemonManager instance;
	private ScriptLoader scriptLoader = new ScriptLoader();

	public static PokemonManager getInstance() {
		if(instance == null)
			instance = new PokemonManager();
		return instance;
	}

	public Pokemon createPokemonByName(final String name, final int level) {
		final PokemonSpecies species = new PokemonSpecies();
		scriptLoader.load(R.pokemonScripts + name + ".dsl", species);
		species.setName(name); // user can't overide, filename is pokemon name to avoid confusion
		return new Pokemon(level, species);
	}
}
