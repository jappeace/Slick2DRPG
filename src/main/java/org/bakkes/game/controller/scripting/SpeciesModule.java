package org.bakkes.game.controller.scripting;

import org.bakkes.game.R;
import org.bakkes.game.model.pokemon.IPokemonSpecies;
import org.bakkes.game.model.pokemon.PokemonSpecies;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

public class SpeciesModule extends AbstractModule{

	private String speciesName;

	public SpeciesModule(){
		this("caterpie");
	}
	public SpeciesModule(final String speciesName){
		this.speciesName = speciesName;
	}

	private ScriptLoader scriptLoader = new ScriptLoader();
	public void setSpeciesName(final String name){
        speciesName = name;
	}


	@Override
	protected void configure(){

	}
	public @Provides IPokemonSpecies provideSpecies(){
		final PokemonSpecies species = new PokemonSpecies();
		scriptLoader.load(R.pokemonScripts + speciesName + ".dsl", species);
		species.setName(speciesName); // user can't overide, filename is pokemon name to avoid confusion
		return species;
	}
}
