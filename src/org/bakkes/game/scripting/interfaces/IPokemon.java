package org.bakkes.game.scripting.interfaces;

import org.bakkes.game.battle.IMove;

public interface IPokemon {
	int get_id();
	String get_name();
	String get_image();
	void initialize_fuzzy();
	int get_desirability();
	int get_fire_strength();
	int get_earth_strength();
	int get_water_strength();
	int get_health();
	
	void set_fire_strength(int strength);
	void set_water_strength(int strength);
	void set_earth_strength(int strength);
	
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
