package org.bakkes.game.controller.scripting.dsl;

import groovy.lang.Closure;

import java.util.Collection;

import org.bakkes.game.R;
import org.bakkes.game.controller.scripting.dsl.anotation.Required;
import org.bakkes.game.controller.scripting.dsl.anotation.Result;
import org.bakkes.game.controller.scripting.loader.ScriptLoader;
import org.bakkes.game.model.battle.move.Move;
import org.bakkes.game.model.pokemon.PokemonSpecies;
import org.bakkes.game.model.pokemon.PokemonStatistics;
import org.bakkes.game.model.pokemon.Type;
import org.newdawn.slick.util.Log;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class PokemonSpeciesDsl extends ASpriteNamedDsl {

	private PokemonSpecies target = new PokemonSpecies();
	@Inject Provider<PokemonStatistics> statisticsProvider;
	@Inject Provider<Move> movesProvider;
	@Inject ScriptLoader loader;
	public final void setEvolution(final String evolution) {
		target.setEvolution(evolution);
	}
	public final void setName(final String name) {
		target.setName(name);
	}
	public final void setEvolutionLevel(final int evolutionLevel) {
		target.setEvolutionLevel(evolutionLevel);
	}
	public void base(final Closure<Void> commands){
		target.setBase(createFromCommands(commands));
	}
	public void onLevelIncrease(final Closure<Void> commands){
		target.setIncrease(createFromCommands(commands));
	}
	private PokemonStatistics createFromCommands(final Closure<Void> commands){
		final PokemonStatistics stats = statisticsProvider.get();
		delegate(commands, stats);
		return stats;
	}
	@Required
	public final void setType(final String type) {
		if(type.equals(Type.unkown.name())){
			Log.warn("the unkown type is only meant for errorhandeling, using it in production code may lead to problems");
		}
		for(final Type t : Type.values()){
			if(t.name().equals(type)){
                target.setType(t);
                return;
			}
		}
		Log.warn("type " + type + " not found, setting to unkown");
		target.setType(Type.unkown);
	}
	public final void setMoves(final Collection<String> moves) {
		for(final String moveName : moves){
			Log.info("reading " + moveName);
			final Move move = movesProvider.get();
			loader.load(R.moveScripts + moveName + ".dsl", move);
			move.setName(moveName); // avoid confusion user can't override name
			target.getMoves().add(move);
		}
	}
	public void setTrainingSpeed(final float trainingSpeed) {
		target.setTrainingSpeed(trainingSpeed);
	}
	@Override
	@Result
	public PokemonSpecies getTarget(){
		return target;
	}

}
