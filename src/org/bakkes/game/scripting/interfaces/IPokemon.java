package org.bakkes.game.scripting.interfaces;

import org.bakkes.game.battle.IMove;

public interface IPokemon {
	int get_id();
	String get_name();
	String get_image();
	void initialize_fuzzy();
	int get_desirability();
	IMove[] get_moves();
	/*
	 * Default initializer for properties
	 */
	void initialize();
	
	/*
	 * Where the data should be set
	 */
	void info();
}
