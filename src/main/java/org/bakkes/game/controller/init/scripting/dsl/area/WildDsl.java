package org.bakkes.game.controller.init.scripting.dsl.area;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import nl.jappieklooster.groovy.meta.IMissingMethodHandler;

import org.bakkes.game.controller.init.scripting.dsl.ADsl;
import org.bakkes.game.model.pokemon.Pokemon;
import org.newdawn.slick.util.Log;

import com.google.inject.Inject;

public class WildDsl extends ADsl implements IMissingMethodHandler{

	private @Inject PokemonDsl dsl;
	private @Inject Random random;
	private List<Pokemon> result = new ArrayList<>();
	private static final int DEFAULT_LEVEL = 5;
	@Override
	public void methodMissing(final String speciesName, final Object args) {
		Log.info("handeling a " + speciesName);
		dsl.setSpecies(speciesName);

		final Object first = ((Object[])args)[0];
		if(first == null){
			dsl.setLevel(DEFAULT_LEVEL);
		}
		if(first instanceof Integer){
			dsl.setLevel((Integer) first);
		}
		if(first instanceof List){
            final List<Integer> levelRange = (List<Integer>) first;
            dsl.setLevel(random.nextInt(levelRange.get(levelRange.size()-1))+levelRange.get(0));
		}

		result.add(dsl.createPokemon());
	}
	public final List<Pokemon> getResult() {
		return result;
	}
}
