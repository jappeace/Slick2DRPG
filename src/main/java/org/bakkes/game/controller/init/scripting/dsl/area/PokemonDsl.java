package org.bakkes.game.controller.init.scripting.dsl.area;

import groovy.lang.Closure;

import org.bakkes.game.controller.init.scripting.SpeciesModule;
import org.bakkes.game.controller.init.scripting.dsl.ADsl;
import org.bakkes.game.model.pokemon.IPokemonSpecies;
import org.bakkes.game.model.pokemon.IPokemonStatistics;
import org.bakkes.game.model.pokemon.Pokemon;
import org.bakkes.game.model.pokemon.PokemonStatistics;
import org.newdawn.slick.util.Log;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class PokemonDsl extends ADsl{
	private IPokemonSpecies species;
    private IPokemonStatistics statistics;
	private int level = 0;
	private @Inject Provider<PokemonStatistics> pokemonStatsProvider;
	public void setSpecies(final String name){
		final SpeciesModule module = new SpeciesModule(name);
		species = module.provideSpecies();
	}

	public void statistics(final Closure<Void> commands){
        statistics = pokemonStatsProvider.get();
        delegate(commands,statistics);
	}
	public void setLevel(final int lvl){
		this.level = lvl;
	}

	public Pokemon createPokemon(){
		if(species == null){
			Log.warn("no species set, going for caterpie");
			species = new SpeciesModule().provideSpecies();
		}
        final Pokemon result = new Pokemon(species, statistics);
		result.setLevel(this.level);
		return result;
	}


}
