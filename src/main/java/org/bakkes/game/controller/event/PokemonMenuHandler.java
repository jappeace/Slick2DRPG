package org.bakkes.game.controller.event;

import java.nio.file.Path;
import java.util.Collection;
import java.util.LinkedList;

import org.bakkes.game.controller.event.input.IKeyListener;
import org.bakkes.game.controller.event.input.Key;
import org.bakkes.game.controller.state.CommonGameState;
import org.bakkes.game.model.SpriteNameExtender;
import org.bakkes.game.model.entity.player.invetory.PokeBelt;
import org.bakkes.game.model.pokemon.Pokemon;
import org.bakkes.game.view.components.IShape;
import org.bakkes.game.view.overworld.PokeView;
import org.bakkes.game.view.overworld.SpritedNameView;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;

public class PokemonMenuHandler implements IMenuHandler{

	private @Inject @Named("from player") PokeBelt belt;
	private @Inject Provider<SpritedNameView> viewsProvider;
	private @Inject Provider<SpriteNameExtender> extenderProvider;
	private @Inject PokeView view;
	private @Inject Provider<CommonGameState> state;
	private @Inject @Named("spritePokemon") Path path;
	@Override
	public void select(final int item) {
		view.setPokemon(belt.getAt(item));
		state.get().setOverlay(view);
		state.get().setKeyListener(new IKeyListener(){

			@Override
			public void KeyDown(final Key key) {
				if(key.isConfirm()){
					state.get().setOverlay(null);
					state.get().setKeyListener(null);
				}
			}

			@Override
			public void KeyUp(final Key key) {
			}

		});
	}

	@Override
	public Collection<IShape> getOptions() {

		final Collection<IShape> result = new LinkedList<>();
		for(final Pokemon pokemon : belt){
			final SpritedNameView view = viewsProvider.get();
			final SpriteNameExtender extender = extenderProvider.get();
			extender.setHasSpriteName(pokemon);
			extender.setExtension("/small");
			view.setNamed(path, extender);
			result.add(view);
		}
		return result;
	}

}
