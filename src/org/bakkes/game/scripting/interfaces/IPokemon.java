package org.bakkes.game.scripting.interfaces;

public interface IPokemon {
	int get_id();
	String get_name();
	String get_image();
	void initialize_fuzzy();
	int get_desirability();
	
	/*
	 * Default initializer for properties
	 */
	void __default();
	
	/*
	 * Where the data should be set
	 */
	void info();
}
