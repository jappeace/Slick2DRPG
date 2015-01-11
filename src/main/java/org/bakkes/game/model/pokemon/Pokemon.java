package org.bakkes.game.model.pokemon;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.bakkes.game.R;
import org.bakkes.game.scripting.ScriptLoader;

public class Pokemon{

	private IPokemonSpecies species;
	private int health;
	private int attack;
	private int defence;
	private int speed;
	private int level = 0;
	private List<IMove> moves = new LinkedList<>();
	private static Random random = new Random();
	public Pokemon(final int level, final IPokemonSpecies species){
		health = species.getBaseHealth();
		defence = species.getBaseDefence();
		attack = species.getBaseAttack();
		speed = species.getBaseSpeed();
		this.species = species;
		for(int i = 0; i < level; i++){
			levelUp();
		}
		final ScriptLoader loader = new ScriptLoader();
		for(final String moveName : species.getMoves()){
			final Move move = new Move();
			loader.load(R.moveScripts + moveName + ".dsl", move);
			move.setName(moveName); // avoid confusion user can't override name
			moves.add(move);
		}
	}

	public void levelUp(){
		level++;
        health += random.nextInt(species.getIncreaseHealth());
        defence += random.nextInt(species.getIncreaseDefence());
        attack += random.nextInt(species.getIncreaseAttack());
        speed += random.nextInt(species.getIncreaseSpeed());
	}
	public final IPokemonSpecies getSpecies() {
		return species;
	}

	public final int getHealth() {
		return health;
	}

	public final int getAttack() {
		return attack;
	}

	public final int getDefence() {
		return defence;
	}

	public final int getSpeed() {
		return speed;
	}

	public final void setHealth(final int health) {
		this.health = health;
	}

	public String getSpritePath(){
		return R.pokemonSprites + getSpecies().getSpriteName() + ".png";
	}

	public List<IMove> getMoves() {
		return moves;
	}
}
