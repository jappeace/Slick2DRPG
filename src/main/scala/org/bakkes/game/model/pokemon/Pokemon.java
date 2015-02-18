package org.bakkes.game.model.pokemon;

import com.google.inject.Inject;
import nl.jappieklooster.annotation.Nullable;
import org.bakkes.game.model.AModel;
import org.bakkes.game.model.IHasSpriteName;
import org.bakkes.game.model.battle.move.IMove;
import org.newdawn.slick.util.Log;

import java.util.List;
import java.util.Random;

public class Pokemon extends AModel implements IHasSpriteName{

	private IPokemonSpecies species;
	private IPokemonStatistics normalStats;
	private PokemonStatistics currentStats;
	private int level = 0;
	private int experiance;
	private static Random random = new Random();
	private boolean isFemale = false;
	private boolean isShiny = false;
	@Inject
	public Pokemon(final IPokemonSpecies species){
		this(species, species.getBase());
	}

	public Pokemon(final IPokemonSpecies species, final @Nullable IPokemonStatistics stats){
		if(species == null){
			throw new IllegalArgumentException("no species set");
		}
		normalStats = species.getBase();
		if(stats != null){
            normalStats = stats;
		}
		this.species = species;
		this.setName(species.getName());
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
		final IPokemonStatistics increase = species.getIncrease();
		for(int i = 0; i < Math.abs(diff); i++){
			if(diff > 0){
                normalStats = normalStats.plus(increase.createFrom(random));
                continue;
			}
            normalStats = normalStats.minus(increase.createFrom(random));
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
	}

	@Override
	public String toString(){
		return getName() + ", current stats: [" + currentStats + "] normal stats: [" + normalStats + "]";
	}

	@Override
	public String getSpriteName() {
		return species.getSpriteName();
	}

	@Override
	public void setSpriteName(final String to) {
		Log.warn("can't set sprite of indivdual pokemon: " + to);
	}

	public boolean isShiny(){
		return isShiny;
	}
	public boolean isFemale(){
		return isFemale;
	}
}
