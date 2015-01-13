package org.bakkes.game.scripting;

import org.bakkes.game.R;
import org.bakkes.game.model.pokemon.IPokemonSpecies;
import org.bakkes.game.model.pokemon.PokemonSpecies;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;

public class SpeciesModule extends AbstractModule{

	private String speciesName;
	private  int level;

	public SpeciesModule(){
		this(1, "caterpie");
	}
	public SpeciesModule(final int level, final String speciesName){
		this.level = level;
		this.speciesName = speciesName;
	}

	private ScriptLoader scriptLoader = new ScriptLoader();
	public void setSpeciesName(final String name){
        speciesName = name;
	}
	public void setLevel(final int level) {
		this.level = level;
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

	public @Named("pokelevel") @Provides int provideLevel() {
		return level;
	}

}
