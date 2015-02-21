package org.bakkes.game.controller.init.scripting.loader

import java.nio.file.Path

import com.google.inject.{Inject, Provider}
import com.google.inject.name.Named
import org.bakkes.game.controller.init.scripting.dsl.PokemonSpeciesDsl
import org.bakkes.game.model.pokemon.PokemonSpecies
import org.newdawn.slick.util.Log

class SpeciesLoader @Inject() (
	@Named("scriptPokemon")
	path:Path,
	scriptLoader:ScriptLoader ,
	dslProvider:Provider[PokemonSpeciesDsl]
) {
	def load(name:String) : PokemonSpecies = {
		val dsl:PokemonSpeciesDsl = dslProvider.get()
		if(!scriptLoader.load(path.resolve( name + ".dsl"), dsl)){
			Log.warn("loading failed of "+ name + ", returning null")
			return null
		}
		// user can't override, filename is pokemon name to avoid confusion
		dsl.getTarget.copy(name = name)
	}
}
