package org.bakkes.game.controller.init.scripting.dsl.area;

import groovy.lang.Closure;

import org.bakkes.game.controller.init.scripting.dsl.ADsl;
import org.bakkes.game.controller.init.scripting.loader.ItemLoader;
import org.bakkes.game.model.entity.player.Player;
import org.bakkes.game.model.map.Tile;
import org.bakkes.game.model.pokemon.Pokemon;
import org.newdawn.slick.util.Log;

import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * manipulate player stats with this dsl (what kind of pokemon etc)
 */
public class PlayerDsl extends ADsl{

	private @Inject Provider<PokemonDsl> pokemonDsl;
	private @Inject Player player;
	private @Inject ItemLoader loader;

	public void giveItem(final String ... items){
		for(final String itemName : items){
            player.getInventory().addItem(loader.load(itemName));
		}
	}

	public void givePokemon(final Closure<Void> commands){
		final PokemonDsl dsl = pokemonDsl.get();
		delegate(commands,dsl);
		final Pokemon poke = dsl.createPokemon();
		player.getPokebelt().add(poke);
		Log.info("player is reciving a " + poke.getName());
	}

	public void position(final int x, final int y){
		player.setPosition(new Tile(x,y).topLeftPixels());
	}

}
