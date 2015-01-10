package org.bakkes.game.scripting;

import org.bakkes.game.battle.IMove;
import org.bakkes.game.scripting.interfaces.IPokemon;

public class PokemonFactory {

	public PokemonFactory() {

	}

	public static IPokemon createNewInstance(final String classname) {
		return new IPokemon(){

			@Override
			public int get_id() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public String get_name() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String get_image() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public void initialize_fuzzy() {
				// TODO Auto-generated method stub

			}

			@Override
			public int get_desirability() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public int get_fire_strength() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public int get_earth_strength() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public int get_water_strength() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public int get_health() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public void set_fire_strength(final int strength) {
				// TODO Auto-generated method stub

			}

			@Override
			public void set_water_strength(final int strength) {
				// TODO Auto-generated method stub

			}

			@Override
			public void set_earth_strength(final int strength) {
				// TODO Auto-generated method stub

			}

			@Override
			public IMove[] get_moves() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public void initialize() {
				// TODO Auto-generated method stub

			}

			@Override
			public void info() {
				// TODO Auto-generated method stub

			}

		};
	}
}
