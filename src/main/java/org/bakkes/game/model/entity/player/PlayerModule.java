package org.bakkes.game.model.entity.player;

import org.bakkes.game.controller.init.scripting.SpeciesModule;
import org.bakkes.game.model.entity.Character;
import org.bakkes.game.model.entity.player.invetory.PokeBelt;
import org.bakkes.game.model.map.Tile;
import org.bakkes.game.model.pokemon.IPokemonSpecies;
import org.bakkes.game.view.PositionModule;

import com.google.inject.Provides;
import com.google.inject.name.Named;

public class PlayerModule extends PositionModule{
	// TODO: load player starting start from script
	private static final int PLAYER_POKE_LEVEL = 20;
	private static final String PLAYER_POKEMON = "charmender";
	private static final Tile STARTING_TILE = new Tile(20,10); // 60,20 before grass

	private SpeciesModule species = new SpeciesModule();

	public PlayerModule(){
		this.setPosition(STARTING_TILE.topLeftPixels());

	}

	@Override
	protected void configure() {
		bind(Character.class).to(Player.class);
	}

	public @Provides @Named("pokelevel") int providePokeLevel(){
		return PLAYER_POKE_LEVEL;
	}

	public @Provides IPokemonSpecies provideSpecies(){
		species.setSpeciesName(PLAYER_POKEMON);
		return species.provideSpecies();
	}

	@Provides
	@Named("player belt")
	public  PokeBelt providePlayerBelt(final Player player){
		return player.getPokebelt();
	}

}
