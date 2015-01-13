package org.bakkes.game.model.pokemon;

import java.util.List;

import org.bakkes.game.GameInfo;
import org.bakkes.game.R;
import org.bakkes.game.model.battle.move.IMove;
import org.newdawn.slick.util.Log;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class Pokemon{

	private IPokemonSpecies species;
	private IPokemonStatistics normalStats;
	private PokemonStatistics currentStats;
	private int level = 0;
	private int experiance;
	private String name = "";
	@Inject
	public Pokemon(@Named("pokelevel") final int level, final IPokemonSpecies species){
		normalStats = species.getBase();
		this.species = species;
		setExperiance(calculateXpFor(level));
		for(int i = 0; i < level; i++){
			levelUp();
		}
		heal();
	}

	private void levelUp(){
		level = getLevel() + 1;
		normalStats = normalStats.plus(species.getIncrease().createFrom(GameInfo.RANDOM));
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
			levelUp();
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

	private void setExperiance(final int experiance) {
		this.experiance = experiance;
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
