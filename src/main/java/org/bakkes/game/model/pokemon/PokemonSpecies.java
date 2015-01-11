package org.bakkes.game.model.pokemon;

import java.util.Arrays;
import java.util.Collection;

import org.newdawn.slick.util.Log;

public class PokemonSpecies implements IPokemonSpecies{
	String spriteName;
	String evolution;
	String name;
	int evolutionLevel;
	int baseHealth = 10;
	int baseAttack = 1;
	int baseDefence = 1;
	int baseSpeed = 1;
	int increaseHealth = 5;
	int increaseAttack = 1;
	int increaseDefence = 1;
	int increaseSpeed = 1;
	Type type;
	Collection<String> moves = Arrays.asList(new String[]{"tackle"});
	@Override
	public final String getSpriteName() {
		return spriteName;
	}
	@Override
	public final String getEvolution() {
		return evolution;
	}
	@Override
	public final String getName() {
		return name;
	}
	@Override
	public final int getEvolutionLevel() {
		return evolutionLevel;
	}
	@Override
	public final int getBaseHealth() {
		return baseHealth;
	}
	@Override
	public final int getBaseAttack() {
		return baseAttack;
	}
	@Override
	public final int getBaseDefence() {
		return baseDefence;
	}
	@Override
	public final int getBaseSpeed() {
		return baseSpeed;
	}
	@Override
	public final int getIncreaseHealth() {
		return increaseHealth;
	}
	@Override
	public final int getIncreaseAttack() {
		return increaseAttack;
	}
	@Override
	public final int getIncreaseDefence() {
		return increaseDefence;
	}
	@Override
	public final int getIncreaseSpeed() {
		return increaseSpeed;
	}
	@Override
	public final Type getType() {
		return type;
	}
	@Override
	public final Collection<String> getMoves() {
		return moves;
	}
	public final void setSpriteName(final String spriteName) {
		this.spriteName = spriteName;
	}
	public final void setEvolution(final String evolution) {
		this.evolution = evolution;
	}
	public final void setName(final String name) {
		this.name = name;
	}
	public final void setEvolutionLevel(final int evolutionLevel) {
		this.evolutionLevel = evolutionLevel;
	}
	public final void setBaseHealth(final int baseHealth) {
		this.baseHealth = baseHealth;
	}
	public final void setBaseAttack(final int baseAttack) {
		this.baseAttack = baseAttack;
	}
	public final void setBaseDefence(final int baseDefence) {
		this.baseDefence = baseDefence;
	}
	public final void setBaseSpeed(final int baseSpeed) {
		this.baseSpeed = baseSpeed;
	}
	public final void setIncreaseHealth(final int increaseHealth) {
		this.increaseHealth = increaseHealth;
	}
	public final void setIncreaseAttack(final int increaseAttack) {
		this.increaseAttack = increaseAttack;
	}
	public final void setIncreaseDefence(final int increaseDefence) {
		this.increaseDefence = increaseDefence;
	}
	public final void setIncreaseSpeed(final int increaseSpeed) {
		this.increaseSpeed = increaseSpeed;
	}
	public final void setType(final String type) {
		if(type.equals(Type.unkown.name())){
			Log.warn("the unkown type is only meant for errorhandeling, using it in production code may lead to problems");
		}
		for(final Type t : Type.values()){
			if(t.name().equals(type)){
                this.type = t;
                return;
			}
		}
		Log.warn("type " + type + " not found, setting to unkown");
		this.type = Type.unkown;
	}
	public final void setMoves(final Collection<String> moves) {
		this.moves = moves;
	}
}
