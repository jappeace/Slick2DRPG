package org.bakkes.game.model.entity.player.invetory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bakkes.game.model.pokemon.Pokemon;

import com.sun.istack.internal.Nullable;

/**
 * contains the pokemon the player has
 * this amount should be small
 *
 * thread safe
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

	/**
	 * the syncrhonized operator togehter with making a copy of the pokemon list makes this class thread safe
	 * @param poke
	 */
	public synchronized void add(final Pokemon poke){
		pokemon.add(poke);
	}

	/**
	 * @return the most recent list, copied and there a iterator from
	 */
	@Override
	public Iterator<Pokemon> iterator() {
		return new ArrayList<>(pokemon).iterator();
	}
}
