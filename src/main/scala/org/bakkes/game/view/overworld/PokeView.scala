package org.bakkes.game.view.overworld

import java.nio.file.Path

import com.google.inject.Inject
import com.google.inject.name.Named
import org.bakkes.game.model.GameInfo
import org.bakkes.game.model.pokemon.Pokemon
import org.bakkes.game.view.{IRenderable, PokemonSprite}
import org.bakkes.game.view.SpriteType._
import org.bakkes.game.view.battle.StatisticsView
import org.newdawn.slick.Graphics

class PokeView @Inject() (
	private val avatar: PokemonSprite,
	private val stats: StatisticsView,
	@Named("spritePokemon")
	private val path: Path
) extends IRenderable {

	def setPokemon(to: Pokemon) {
		avatar.spriteType = big
		avatar.setPokemon(to)
		stats.setPokemon(to)
		val margin: Float = 40
		stats.x(GameInfo.SCREEN_WIDTH - stats.width - margin)
		stats.y(margin)
	}

	def render(g: Graphics) {
		avatar.render(g)
		stats.render(g)
	}
}