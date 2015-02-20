package org.bakkes.game.controller.state.overworld.event

import java.nio.file.Path
import java.util.Collection

import com.google.inject.name.Named
import com.google.inject.{Inject, Provider}
import org.bakkes.game.controller.state.event.IMenuHandler
import org.bakkes.game.controller.state.event.input.{IKeyListener, Key}
import org.bakkes.game.controller.state.CommonGameState
import org.bakkes.game.model.entity.player.invetory.PokeBelt
import org.bakkes.game.view.SpriteType
import org.bakkes.game.view.components.{IShape, SpritWithString}
import org.bakkes.game.view.overworld.PokeView

import scala.collection.JavaConversions._

class PokemonMenuHandler @Inject() (
	@Named("from player")
	private val belt: PokeBelt,
	private val viewsProvider: Provider[SpritWithString] ,
	private val view: PokeView ,
	private val state: Provider[CommonGameState] ,
	@Named("spritePokemon")
	private val path: Path
) extends IMenuHandler {

	def select(item: Int) {
		view.setPokemon(belt.getAt(item))
		state.get.setOverlay(view)
		state.get.setKeyListener( new IKeyListener{
			override def KeyDown(key: Key) {
				if (key.isConfirm) {
					state.get.setOverlay(null)
					state.get.setKeyListener(null)
				}
			}
			override def KeyUp(key: Key) {
			}
		})
	}

	def getOptions: Collection[IShape] = {
		val result = belt.map{ pokemon =>
			val view: SpritWithString = viewsProvider.get
			view.setNamed(
				path.resolve(SpriteType.small.getImageName(pokemon)),
				pokemon.getName()
			)
			view
		}
		return result
	}
}