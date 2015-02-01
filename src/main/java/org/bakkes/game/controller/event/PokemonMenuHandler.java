package org.bakkes.game.controller.event;

import java.util.Collection;
import java.util.LinkedList;

import org.bakkes.game.controller.ShapesConverter;
import org.bakkes.game.model.entity.player.invetory.PokeBelt;
import org.bakkes.game.model.pokemon.Pokemon;
import org.bakkes.game.view.components.IShape;
import org.newdawn.slick.util.Log;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class PokemonMenuHandler implements IMenuHandler{

	private @Inject @Named("from player") PokeBelt belt;
	private @Inject ShapesConverter converter;
	@Override
	public void select(final int item) {
		Log.info(belt.getAt(item).toString());
	}

	@Override
	public Collection<IShape> getOptions() {
		final Collection<String> result = new LinkedList<>();
		for(final Pokemon pokemon : belt){
			result.add(pokemon.getName());
		}
		return converter.convert(result);
	}

}
