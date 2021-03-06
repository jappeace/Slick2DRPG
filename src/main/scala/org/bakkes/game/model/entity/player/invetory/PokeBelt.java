package org.bakkes.game.model.entity.player.invetory;

import nl.jappieklooster.annotation.Nullable;
import org.bakkes.game.model.pokemon.Pokemon;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * contains the pokemon the player has
 * this amount should be small
 *
 * thread safe
 */
public class PokeBelt implements Iterable<Pokemon> {
	private List<Pokemon> pokemon = new ArrayList<>();

	public boolean isEmpty(){
		return pokemon.isEmpty();
	}
	public @Nullable Pokemon getFirstAlive(){
		for(final Pokemon poke : pokemon){
			if(poke.isAlive()){
				return poke;
			}
		}
		return null;
	}

	/**
	 * the syncrhonized operator togehter with making a copy of the pokemon list makes this class thread safe
	 * @param poke
	 */
	public void add(final Pokemon poke){
		if(poke == null){
			throw new NullPointerException("null not allowed");
		}
		pokemon.add(poke);
	}

	/**
	 * @return the most recent list, copied and there a iterator from
	 */
	@Override
	public Iterator<Pokemon> iterator() {
		return pokemon.iterator();
	}

	public Pokemon getAt(final int index){
		return pokemon.get(index);
	}
}
