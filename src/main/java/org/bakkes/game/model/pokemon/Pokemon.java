package org.bakkes.game.model.pokemon;

import java.util.List;
import java.util.Random;

import org.bakkes.game.R;
import org.bakkes.game.model.battle.move.IMove;
import org.newdawn.slick.util.Log;

import com.google.inject.Inject;

public class Pokemon{

	private IPokemonSpecies species;
	private IPokemonStatistics normalStats;
	private PokemonStatistics currentStats;
	private int level = 0;
	private int experiance;
	private String name = "";
	private @Inject Random random;
	@Inject
	public Pokemon(final IPokemonSpecies species){
		this(species, species.getBase());
	}

	public Pokemon(final IPokemonSpecies species, final IPokemonStatistics stats){
		normalStats = stats;
		this.species = species;
		heal();

	}
	/**
	 * level up to a specific level, this has all the user fuckup gaurds
	 * @param lvl
	 */
	public void setLevel(final int lvl){
		if(lvl < 0){
			// I'm not gonna implement negitve levels
			throw new IllegalArgumentException("trying to level up to a negative level");
		}
		setExperiance(calculateXpFor(lvl));
		if(lvl > level){
			// this is probably a bug
			Log.warn("leveling down?");
		}
		level(lvl - level);
		heal();
	}

	private void level(final int diff){
		level = getLevel() + diff;
		for(int i = 0; i < Math.abs(diff); i++){
			if(diff > 0){
                normalStats = normalStats.plus(species.getIncrease().createFrom(random));
                continue;
			}
            normalStats = normalStats.minus(species.getIncrease().createFrom(random));
		}
	}
	public final IPokemonSpecies getSpecies() {
		return species;
	}
	public void heal(){
		currentStats = new PokemonStatistics(normalStats);
	}

	public final void damage(final int dmg) {
		currentStats.setHealth(currentStats.getHealth() - dmg);
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
		setExperiance(getExperiance() + exp);
		final int nextLevel = calculateXpFor(level + 1);
		Log.info("current xp = " + getExperiance());
		Log.info("next level xp = " + nextLevel);
		if(getExperiance() > nextLevel){
			Log.info("level up!!");
            final PokemonStatistics current = new PokemonStatistics(normalStats);
			level(1);
			return normalStats.minus(current).plus(addExperiance(0)); // recursive to make sure lvl match xp (especialy usefull for low level battles
		}
		return null; // no leveling
	}
	public int calculateXpFor(final int lvl){
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

	public int getExperiance() {
		return experiance;
	}

	/**
	 * set the experiance and adjust the level without changing the statistics
	 * @param experiance
	 */
	public void setExperiance(final int experiance) {
		if(experiance < 0){
			throw new IllegalArgumentException("negative experiance not suported, (yes this games gives only positive experiances)");
		}
		if(experiance < this.experiance){
			// this function will not level down
			Log.warn("setting experiance to a lower amount than before?");
		}
		this.experiance = experiance;
		while(calculateXpFor(getLevel()) < experiance){
			level++;
		}
	}

	public String getName() {
		if(name.isEmpty()){
			name = species.getName();
		}
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}
}
