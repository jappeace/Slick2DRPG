package org.bakkes.game.controller.event

import java.nio.file.Path
import java.util.Collection
import java.util.LinkedList
import org.bakkes.game.controller.event.input.IKeyListener
import org.bakkes.game.controller.event.input.Key
import org.bakkes.game.controller.state.CommonGameState
import org.bakkes.game.model.SpriteNameExtender
import org.bakkes.game.model.entity.player.invetory.PokeBelt
import org.bakkes.game.model.pokemon.Pokemon
import org.bakkes.game.view.components.IShape
import org.bakkes.game.view.overworld.PokeView
import org.bakkes.game.view.overworld.SpritedNameView
import com.google.inject.Inject
import com.google.inject.Provider
import com.google.inject.name.Named
import scala.collection.JavaConversions._

class PokemonMenuHandler @Inject() (
	@Named("from player")
	private val belt: PokeBelt,
	private val viewsProvider: Provider[SpritedNameView] ,
	private val extenderProvider: Provider[SpriteNameExtender] ,
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
			val view: SpritedNameView = viewsProvider.get
			val extender: SpriteNameExtender = extenderProvider.get
			extender.setHasSpriteName(pokemon)
			extender.spriteNameExtender.after = Option("/small")
			view.setNamed(path, extender)
			view
		}
		return result
	}
}