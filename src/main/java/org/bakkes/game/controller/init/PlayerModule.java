package org.bakkes.game.controller.init;

import org.bakkes.game.controller.scripting.SpeciesModule;
import org.bakkes.game.model.entity.Entity;
import org.bakkes.game.model.entity.Player;
import org.bakkes.game.model.map.Tile;
import org.bakkes.game.model.pokemon.IPokemonSpecies;
import org.bakkes.game.view.PositionModule;

import com.google.inject.Provides;
import com.google.inject.name.Named;

public class PlayerModule extends PositionModule{
	// TODO: load player starting start from script
	private static final int PLAYER_POKE_LEVEL = 10;
	private static final String PLAYER_POKEMON = "charmender";
	private static final Tile STARTING_TILE = new Tile(60,20);

	private SpeciesModule species = new SpeciesModule();

	public PlayerModule(){
		this.setPosition(STARTING_TILE.topLeftPixels());

	}

	@Override
	protected void configure() {
		bind(Entity.class).to(Player.class);
	}

	@Provides @Named("pokelevel") int providePokeLevel(){
		return PLAYER_POKE_LEVEL;
	}

	public @Provides IPokemonSpecies provideSpecies(){
		species.setSpeciesName(PLAYER_POKEMON);
		return species.provideSpecies();
	}

}
