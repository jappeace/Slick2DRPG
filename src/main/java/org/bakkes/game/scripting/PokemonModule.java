package org.bakkes.game.scripting;

import org.bakkes.game.GameInfo;
import org.bakkes.game.model.pokemon.Pokemon;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;

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

	public @Named("player-pokemon") @Provides Pokemon providePlayerPokemon(){
		return GameInfo.getInstance().player.getPokemon();
	}

	public void setPokemon(final Pokemon pokemon) {
		this.pokemon = pokemon;
	}

}
