package org.bakkes.game.controller.state;

import org.bakkes.game.AModule;
import org.bakkes.game.model.Bean;
import org.bakkes.game.model.map.Tile;

import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;

/**
 * TODO: this module should probably not be part of the main initilisation squence but one tier lower
 */
public class OverworldModule extends AModule{

	@Override
	public void configure(){
		// wtf, if it works.. This is not guices' fault, java has a horrible generic implementation
		// at compile time the generic flags are stripped away (after the checks) and replaced by
		// object, this means at runtime List<String> = List<Integer>, guice fixes this with type literals
		bind(new TypeLiteral<Bean<Tile>>(){}).in(Singleton.class);
	}
}
