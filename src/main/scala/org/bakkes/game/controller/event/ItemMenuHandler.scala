package org.bakkes.game.controller.event

import java.nio.file.Path
import java.util.Collection

import com.google.inject.{Inject, Provider}
import com.google.inject.name.Named
import org.bakkes.game.model.entity.player.invetory.Inventory
import org.bakkes.game.view.components.IShape
import org.bakkes.game.view.overworld.SpritedNameView

import scala.collection.JavaConversions._

class ItemMenuHandler @Inject() (
	@Named("from player")
	private val inventory: Inventory,
	private val viewsProvider: Provider[SpritedNameView],
	@Named("spriteItems")
	private val path: Path
) extends IMenuHandler {
	def select(item: Int) {
	}

	def getOptions: Collection[IShape] = {
		val result = inventory.map{ item =>
			val result = viewsProvider.get()
			result.setNamed(
				path.resolve(item.getSpriteName+".png"),
				"{}x {}".format(item.amount, item.getName))
			result
		}
		return result
	}
}