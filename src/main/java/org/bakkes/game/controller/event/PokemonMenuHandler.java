package org.bakkes.game.controller.event;

import java.util.Collection;
import java.util.LinkedList;

import org.bakkes.game.model.entity.player.invetory.PokeBelt;
import org.bakkes.game.model.pokemon.Pokemon;
import org.newdawn.slick.util.Log;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class PokemonMenuHandler implements IMenuHandler{

	private @Inject @Named("from player") PokeBelt belt;
	@Override
	public void select(final int item) {
		Log.info(belt.getAt(item).toString());
	}

	@Override
	public Iterable<String> getOptions() {
		final Collection<String> result = new LinkedList<>();
		for(final Pokemon pokemon : belt){
			result.add(pokemon.getName());
		}
		return result;
	}

}
