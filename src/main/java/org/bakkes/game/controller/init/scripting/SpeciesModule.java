package org.bakkes.game.controller.init.scripting;

import java.nio.file.Path;

import org.bakkes.game.AModule;
import org.bakkes.game.controller.init.scripting.loader.ScriptLoader;
import org.bakkes.game.model.Bean;
import org.bakkes.game.model.pokemon.IPokemonSpecies;
import org.bakkes.game.model.pokemon.PokemonSpecies;

import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Named;
import com.google.inject.name.Names;

public class SpeciesModule extends AModule{

	private static final String DEFAULT_SPECIES = "caterpie";

	@Override
	public void configure(){
		bind(new TypeLiteral<Bean<String>>(){}).annotatedWith(Names.named("species name")).to(new TypeLiteral<Bean<String>>(){}).in(Singleton.class);;
	}

	public @Provides IPokemonSpecies provideSpecies(
			@Named("scriptPokemon") final Path path,
            final ScriptLoader scriptLoader,
            @Named("species name") final Bean<String> speciesName
        ){
		if(speciesName.getData() == null){
			speciesName.setData(DEFAULT_SPECIES);
		}
		final PokemonSpecies species = new PokemonSpecies();
		if(!scriptLoader.load(path.resolve( speciesName.getData() + ".dsl"), species)){
            scriptLoader.load(path.resolve(DEFAULT_SPECIES + ".dsl"), species);
		}
		species.setName(speciesName.getData()); // user can't overide, filename is pokemon name to avoid confusion
		return species;
	}
}
