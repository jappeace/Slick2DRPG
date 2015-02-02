package org.bakkes.game.controller.init.scripting;

import org.bakkes.game.AModule;
import org.bakkes.game.R;
import org.bakkes.game.controller.init.scripting.loader.ScriptLoader;
import org.bakkes.game.model.pokemon.IPokemonSpecies;
import org.bakkes.game.model.pokemon.PokemonSpecies;

import com.google.inject.Provides;

public class SpeciesModule extends AModule{

	private String speciesName;
	private static final String DEFAULT_SPECIES = "caterpie";

	public SpeciesModule(){
		this(DEFAULT_SPECIES);
	}
	public SpeciesModule(final String speciesName){
		this.speciesName = speciesName;
	}

	private ScriptLoader scriptLoader = new ScriptLoader();
	public void setSpeciesName(final String name){
        speciesName = name;
	}

	public @Provides IPokemonSpecies provideSpecies(){
		final PokemonSpecies species = new PokemonSpecies();
		if(!scriptLoader.load(R.pokemonScripts + speciesName + ".dsl", species)){
            scriptLoader.load(R.pokemonScripts + DEFAULT_SPECIES + ".dsl", species);
            species.setName(speciesName);
		}
		species.setName(speciesName); // user can't overide, filename is pokemon name to avoid confusion
		return species;
	}
}
