package org.bakkes.game.controller.event;

import com.google.inject.Inject;
import org.bakkes.game.controller.ShapesConverter;
import org.bakkes.game.view.components.IShape;

import java.util.Collection;
import java.util.LinkedList;



public class MainMenuHandler implements IMenuHandler {
	private enum Option{
		Pokemon,
		Items;
	}
	private @Inject MenuShower shower;
	private @Inject PokemonMenuHandler pokeHandler;
	private @Inject ItemMenuHandler itemHandler;
	private @Inject ShapesConverter converter;
	@Override
	public void select(final int item) {
		final Option opt = Option.values()[item];
		switch(opt){
		case Pokemon:
            shower.showHandler(pokeHandler);
			break;
		case Items:
            shower.showHandler(itemHandler);
			break;
		}
	}

	@Override
	public Collection<IShape> getOptions() {
		final Collection<String> result = new LinkedList<>();
		for(final Option option : Option.values()){
			result.add(option.name());
		}
		return converter.convert(result);
	}
}
