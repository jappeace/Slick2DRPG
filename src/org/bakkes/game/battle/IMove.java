package org.bakkes.game.battle;

import org.bakkes.game.scripting.interfaces.IPokemon;

public interface IMove {
	public void execute(IPokemon entity, IPokemon enemy);
	public float get_desirability(IPokemon entity, IPokemon enemy);
	public void init_fuzzy();
	public String get_name();
}
