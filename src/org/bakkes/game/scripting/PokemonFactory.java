package org.bakkes.game.scripting;

import org.bakkes.game.battle.IMove;
import org.bakkes.game.scripting.interfaces.IPokemon;
import org.python.core.PyObject;

public class PokemonFactory {
	
	public PokemonFactory() {
		
	}
	
	public static IPokemon createNewInstance(String classname) {
		System.out.println("Trying to retrieve pokemon " + classname);
		PyObject theClass = ScriptManager.getInterpreter().get(classname);
		PyObject theInstance = theClass.__call__();
		IPokemon t = (IPokemon)theInstance.__tojava__(IPokemon.class);
		t.initialize();
		t.info();
		for(IMove m : t.get_moves()) {
			m.init_fuzzy();
		}
		System.out.println(classname + " initialized");
		return t;
	}
}
