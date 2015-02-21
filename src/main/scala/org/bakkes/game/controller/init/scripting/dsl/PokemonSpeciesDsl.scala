package org.bakkes.game.controller.init.scripting.dsl

import com.google.inject.Inject
import com.google.inject.Provider
import com.google.inject.name.Named
import groovy.lang.Closure
import org.bakkes.game.controller.init.scripting.dsl.anotation.Required
import org.bakkes.game.controller.init.scripting.dsl.anotation.Result
import org.bakkes.game.controller.init.scripting.loader.{SpeciesLoader, ScriptLoader}
import org.bakkes.game.model.pokemon.PokemonSpecies
import org.bakkes.game.model.pokemon.PokemonStatistics
import org.bakkes.game.model.pokemon.Type
import org.newdawn.slick.util.Log
import java.nio.file.Path
import java.util.Collection

class PokemonSpeciesDsl @Inject()(
	private var target: PokemonSpecies,
	private val statisticsProvider: Provider[PokemonStatistics],
	private val scriptLoader: ScriptLoader,
	@Named("moves")
	private val path: Path,
	private val speciesLoader: SpeciesLoader
) extends ADsl{
	final def setEvolution(evolution: String) {
		target = target.copy(
			evolution = Some(speciesLoader.load(evolution))
		)
	}

	final def setName(name: String) {
		Log.warn("this name will be overriden: " + name + " to avoid confusion")
		target = target.copy(
			name = name
		)
	}

	final def setEvolutionLevel(evolutionLevel: Int) {
		target = target.copy(
			evolutionLevel = evolutionLevel
		)
	}

	def base(commands: Closure[Void]) {
		target = target.copy(
			base = createFromCommands(commands)
		)
	}

	def onLevelIncrease(commands: Closure[Void]) {
		target = target.copy(
			increase = createFromCommands(commands)
		)
	}

	private def createFromCommands(commands: Closure[Void]): PokemonStatistics = {
		val stats: PokemonStatistics = statisticsProvider.get
		delegate(commands, stats)
		return stats
	}

	@Required final def setType(element: String) {
		if (element == Type.unkown.name) {
			Log.warn("the unkown type is only meant for errorhandeling, using it in production code may lead to problems")
		}
		for (t <- Type.values) {
			if (t.name == element) {
				target = target.copy(
					element = t
				)
				return
			}
		}
		Log.warn("type " + element + " not found, setting to unkown")
		target = target.copy(
			element = Type.unkown
		)
	}

	final def setMoves(moves: Collection[String]) {
		import scala.collection.JavaConversions._
		for (moveName <- moves) {
			Log.info("reading " + moveName)
			val factory: MoveDsl = new MoveDsl(moveName)
			scriptLoader.load(path.resolve(moveName + ".dsl"), factory)
			target.copy(
				moves =  target.moves :+ factory.createMove
			)
		}
	}

	def setTrainingSpeed(trainingSpeed: Float) {
		target = target.copy(
			trainingSpeed = trainingSpeed
		)
	}

	@Result
	def getTarget: PokemonSpecies = target
}