package org.bakkes.game.controller.init.scripting.dsl.area;

import groovy.lang.Closure;

import org.bakkes.game.controller.init.scripting.SpeciesModule;
import org.bakkes.game.controller.init.scripting.dsl.ADsl;
import org.bakkes.game.model.pokemon.IPokemonSpecies;
import org.bakkes.game.model.pokemon.IPokemonStatistics;
import org.bakkes.game.model.pokemon.Pokemon;
import org.bakkes.game.model.pokemon.PokemonStatistics;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class PokemonDsl extends ADsl{
	private IPokemonSpecies species;
    private IPokemonStatistics statistics;
	private int xp = 0;
	private @Inject Provider<PokemonStatistics> pokemonStatsProvider;
	public void setSpecies(final String name){
		final SpeciesModule module = new SpeciesModule(name);
		species = module.provideSpecies();
	}

	public void statistics(final Closure<Void> commands){
        statistics = pokemonStatsProvider.get();
        delegate(commands,statistics);
	}

	public void setExperiance(final int to){
		xp = to;
	}

	public Pokemon createPokemon(){
		final Pokemon result = new Pokemon(species, statistics);
		result.setExperiance(xp);
		return result;
	}


}
