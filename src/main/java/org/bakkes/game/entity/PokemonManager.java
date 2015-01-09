package org.bakkes.game.entity;

import org.bakkes.game.scripting.PokemonFactory;
import org.bakkes.game.scripting.ScriptManager;
import org.bakkes.game.scripting.interfaces.IPokemon;

public class PokemonManager {

	private static PokemonManager instance;
	
	public PokemonManager getInstance() {
		if(instance == null)
			instance = new PokemonManager();
		return instance;
	}
	
	public static IPokemon getPokemonById(int id) {
		return PokemonFactory.createNewInstance("pokemon_" + id);
	}
}
