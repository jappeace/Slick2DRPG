package org.bakkes.game.model.pokemon;

import java.util.List;
import java.util.Random;

import org.bakkes.game.R;
import org.newdawn.slick.util.Log;

public class Pokemon{

	private IPokemonSpecies species;
	private IPokemonStatistics normalStats;
	private PokemonStatistics currentStats;
	private int level = 0;
	private static Random random = new Random();
	private int experiance;
	public Pokemon(final int level, final IPokemonSpecies species){
		normalStats = species.getBase();
		this.species = species;
		experiance = calculateXpFor(level);
		for(int i = 0; i < level; i++){
			levelUp();
		}
		heal();
	}

	private void levelUp(){
		level = getLevel() + 1;
		normalStats = normalStats.plus(species.getIncrease().createFrom(random));
	}
	public final IPokemonSpecies getSpecies() {
		return species;
	}
	public void heal(){
		currentStats = new PokemonStatistics(normalStats);
	}

	public final void setHealth(final int health) {
		currentStats.setHealth(health);
	}

	public String getSpritePath(){
		return R.pokemonSprites + getSpecies().getSpriteName() + ".png";
	}

	public List<IMove> getMoves() {
		return species.getMoves();
	}
	/**
	 * @param exp
	 * @return if levelup show difference
	 */
	public IPokemonStatistics addExperiance(final int exp){
		if(exp < 0){
			Log.warn("adding negative experiance?");
		}
		experiance += exp;
		final int nextLevel = calculateXpFor(level + 1);
		Log.info("current xp = " + experiance);
		Log.info("next level xp = " + nextLevel);
		if(experiance > nextLevel){
            final PokemonStatistics current = new PokemonStatistics(normalStats);
			levelUp();
			return normalStats.minus(current);
		}
		return null; // no leveling
	}
	private int calculateXpFor(final int lvl){
		return (int)(4 * Math.pow(lvl, 3)/(4 + species.getTrainingSpeed()));

	}
	public final IPokemonStatistics getNormalStats() {
		return normalStats;
	}

	public final IPokemonStatistics getCurrentStats() {
		return currentStats;
	}
	public boolean isAlive(){
		return currentStats.getHealth() > 0;
	}

	public int getLevel() {
		return level;
	}
}
