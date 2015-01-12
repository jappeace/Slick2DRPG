package org.bakkes.game.model.pokemon;

import groovy.lang.Closure;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.bakkes.game.R;
import org.bakkes.game.model.battle.move.IMove;
import org.bakkes.game.model.battle.move.Move;
import org.bakkes.game.scripting.ScriptLoader;
import org.newdawn.slick.util.Log;

public class PokemonSpecies implements IPokemonSpecies{
	String spriteName;
	String evolution;
	String name;
	int evolutionLevel;
	PokemonStatistics base = new PokemonStatistics();
	PokemonStatistics increase = new PokemonStatistics();
	private float trainingSpeed = 1;

	public PokemonSpecies(){
		increase.health = 10;
		increase.attack = 3;
		increase.defence = 3;
		increase.speed = 3;
	}

    final ScriptLoader loader = new ScriptLoader();
	Type type;
	List<IMove> moves = new LinkedList<>();
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
	public void base(final Closure commands){
		commands.setDelegate(base);
		commands.call();
	}
	public void onLevelIncrease(final Closure commands){
		commands.setDelegate(increase);
		commands.call();
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
		for(final String moveName : moves){
			Log.info("reading " + moveName);
			final Move move = new Move();
			loader.load(R.moveScripts + moveName + ".dsl", move);
			move.setName(moveName); // avoid confusion user can't override name
			this.moves.add(move);
		}
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
}
