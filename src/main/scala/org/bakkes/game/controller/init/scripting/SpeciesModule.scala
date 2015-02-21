package org.bakkes.game.controller.init.scripting

import com.google.inject.Provides
import com.google.inject.Singleton
import com.google.inject.TypeLiteral
import com.google.inject.name.Named
import com.google.inject.name.Names
import net.codingwell.scalaguice.ScalaModule
import org.bakkes.game.AModule
import org.bakkes.game.controller.init.scripting.loader.SpeciesLoader
import org.bakkes.game.model.Bean
import org.bakkes.game.model.pokemon.IPokemonSpecies
import org.bakkes.game.model.pokemon.PokemonSpecies

object SpeciesModule {
	private val DEFAULT_SPECIES: String = "caterpie"
}

class SpeciesModule extends AModule with ScalaModule {
	override def configure() {
		val typeLiteral = new TypeLiteral[Bean[String]]{}
		bind(typeLiteral).annotatedWith(
			Names.named("species name")
		).to(typeLiteral).in(classOf[Singleton])
	}

	@Provides def provideSpecies(@Named("species name") speciesName: Bean[String], loader: SpeciesLoader): IPokemonSpecies = {
		var name: String = speciesName.getData
		if (name == null) {
			name = SpeciesModule.DEFAULT_SPECIES
		}
		var result: PokemonSpecies = loader.load(name)
		if (result == null) {
			result = loader.load(SpeciesModule.DEFAULT_SPECIES)
		}
		result = result.copy(name = name)
		return result
	}
}