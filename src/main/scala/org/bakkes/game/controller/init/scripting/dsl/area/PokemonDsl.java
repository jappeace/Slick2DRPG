package org.bakkes.game.controller.init.scripting.dsl.area;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;
import groovy.lang.Closure;
import org.bakkes.game.controller.init.scripting.dsl.ADsl;
import org.bakkes.game.model.Bean;
import org.bakkes.game.model.pokemon.IPokemonSpecies;
import org.bakkes.game.model.pokemon.IPokemonStatistics;
import org.bakkes.game.model.pokemon.Pokemon;
import org.bakkes.game.model.pokemon.PokemonStatistics;

public class PokemonDsl extends ADsl{
    private IPokemonStatistics statistics;
	private int level = 0;
	private @Inject Provider<PokemonStatistics> pokemonStatsProvider;
	private @Inject Provider<IPokemonSpecies> speciesProvider;

	/**
	 * name bean passes the species name to the module that creates the species
	 */
	private @Inject @Named("species name") Bean<String> nameBean;
	public void setSpecies(final String name){
		nameBean.setData(name);
	}

	public void statistics(final Closure<Void> commands){
        statistics = pokemonStatsProvider.get();
        delegate(commands,statistics);
	}
	public void setLevel(final int lvl){
		this.level = lvl;
	}

	public Pokemon createPokemon(){
        final Pokemon result = new Pokemon(speciesProvider.get(), statistics);
		result.setLevel(this.level);
		return result;
	}


}
