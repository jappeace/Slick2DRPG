package org.bakkes.game.controller.event;

import java.util.Collection;
import java.util.LinkedList;

import org.bakkes.game.R;
import org.bakkes.game.model.SpriteNameExtender;
import org.bakkes.game.model.entity.player.invetory.PokeBelt;
import org.bakkes.game.model.pokemon.Pokemon;
import org.bakkes.game.view.components.IShape;
import org.bakkes.game.view.overworld.SpritedNameView;
import org.newdawn.slick.util.Log;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;

public class PokemonMenuHandler implements IMenuHandler{

	private @Inject @Named("from player") PokeBelt belt;
	private @Inject Provider<SpritedNameView> viewsProvider;
	private @Inject Provider<SpriteNameExtender> extenderProvider;
	@Override
	public void select(final int item) {
		Log.info(belt.getAt(item).toString());
	}

	@Override
	public Collection<IShape> getOptions() {

		final Collection<IShape> result = new LinkedList<>();
		for(final Pokemon pokemon : belt){
			final SpritedNameView view = viewsProvider.get();
			final SpriteNameExtender extender = extenderProvider.get();
			extender.setHasSpriteName(pokemon);
			extender.setExtension("/small");
			view.setNamed(R.pokemonSprites, extender);
			result.add(view);
		}
		return result;
	}

}
