package org.bakkes.game.model.pokemon;

import java.util.LinkedList;
import java.util.List;

import org.bakkes.game.controller.init.scripting.loader.IScriptLoadableType;
import org.bakkes.game.model.IHasSpriteName;
import org.bakkes.game.model.battle.move.IMove;

public class PokemonSpecies implements IPokemonSpecies, IHasSpriteName, IScriptLoadableType{
	private String spriteName;
	/**
	 * empty string means no evolution (final form)
	 */
	private String evolution = "";
	private String name;
	private int evolutionLevel;
	private PokemonStatistics base = new PokemonStatistics();
	private PokemonStatistics increase = new PokemonStatistics();
	private float trainingSpeed = 1;
	private Type type;
	private List<IMove> moves = new LinkedList<>();

	public PokemonSpecies(){
		increase.health = 10;
		increase.attack = 3;
		increase.defence = 3;
		increase.speed = 3;
	}

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
	public final Type getType() {
		return type;
	}
	@Override
	public final List<IMove> getMoves() {
		return moves;
	}
	@Override
	public float getTrainingSpeed() {
		return trainingSpeed;
	}
	@Override
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
	public final void setType(final Type type) {
		this.type = type;
	}
	public final void setMoves(final List<IMove> moves) {
		this.moves = moves;
	}
	public void setTrainingSpeed(final float trainingSpeed) {
		this.trainingSpeed = trainingSpeed;
	}
	@Override
	public IPokemonStatistics getBase() {
		return base;
	}
	@Override
	public IPokemonStatistics getIncrease() {
		return increase;
	}
	public void setBase(final PokemonStatistics to){
		this.base = to;
	}
	public void setIncrease(final PokemonStatistics to){
		this.increase = to;
	}
}
