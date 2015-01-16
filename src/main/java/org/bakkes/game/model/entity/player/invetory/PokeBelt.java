package org.bakkes.game.model.entity.player.invetory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bakkes.game.model.pokemon.Pokemon;

import com.sun.istack.internal.Nullable;

/**
 * contains the pokemon the player has
 */
public class PokeBelt implements Iterable<Pokemon> {
	private List<Pokemon> pokemon = new ArrayList<>();

	public @Nullable Pokemon getFirstAlive(){
		for(final Pokemon poke : pokemon){
			if(poke.isAlive()){
				return poke;
			}
		}
		return null;
	}

	public void add(final Pokemon poke){
		pokemon.add(poke);
	}

	@Override
	public Iterator<Pokemon> iterator() {
		return pokemon.iterator();
	}
}
