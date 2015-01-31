package org.bakkes.game.controller.event;

import java.util.Collection;
import java.util.LinkedList;

import com.google.inject.Inject;



public class MainMenuHandler implements IMenuHandler {
	private enum Option{
		Pokemon,
		Items;
	}
	private @Inject MenuShower shower;
	private @Inject PokemonMenuHandler pokeHandler;
	@Override
	public void select(final int item) {
		final Option opt = Option.values()[item];
		switch(opt){
		case Pokemon:
			pokemon();
			break;
		case Items:
			items();
			break;
		}
	}

	@Override
	public Iterable<String> getOptions() {
		final Collection<String> result = new LinkedList<>();
		for(final Option option : Option.values()){
			result.add(option.name());
		}
		return result;
	}

	private void pokemon(){
		shower.setMenuHandler(pokeHandler);
		shower.show();
	}
	private void items(){
	}
}
