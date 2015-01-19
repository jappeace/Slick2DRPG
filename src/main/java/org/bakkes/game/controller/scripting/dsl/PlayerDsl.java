package org.bakkes.game.controller.scripting.dsl;

import groovy.lang.Closure;

import org.bakkes.game.R;
import org.bakkes.game.controller.scripting.ScriptLoader;
import org.bakkes.game.model.entity.player.Player;
import org.bakkes.game.model.pokemon.Pokemon;
import org.newdawn.slick.util.Log;

import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * manipulate player stats with this dsl (what kind of pokemon etc)
 */
public class PlayerDsl extends ADsl{

	private @Inject Provider<ItemDsl> itemDslProvider;
	private @Inject Provider<PokemonDsl> pokemonDsl;
	private @Inject ScriptLoader loader;
	private @Inject Player player;

	public void giveItem(final String ... items){
		for(final String itemName : items){
			final ItemDsl dsl = itemDslProvider.get();
			loader.load(R.itemScripts + itemName + ".dsl", dsl);
			dsl.setItemName(itemName);
            player.getInventory().addItem(dsl.getItem());
		}
	}

	public void givePokemon(final Closure commands){
		final PokemonDsl dsl = pokemonDsl.get();
		delegate(commands,dsl);
		final Pokemon poke = dsl.createPokemon();
		player.getPokebelt().add(poke);
		Log.info("player is reciving a " + poke.getName());
	}


}
