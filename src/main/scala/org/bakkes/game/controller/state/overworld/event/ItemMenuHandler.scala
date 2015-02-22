package org.bakkes.game.controller.state.overworld.event

import java.nio.file.Path
import java.util.Collection

import com.google.inject.name.Named
import com.google.inject.{Inject, Provider}
import org.bakkes.game.controller.state.event.IMenuHandler
import org.bakkes.game.model.entity.player.invetory.Inventory
import org.bakkes.game.view.components.{Sprite, IShape, ShapeWithString}

import scala.collection.JavaConversions._

class ItemMenuHandler @Inject() (
	@Named("from player")
	private val inventory: Inventory,
	private val shapeProvider: Provider[ShapeWithString],
	private val spriteProvider: Provider[Sprite],
	@Named("spriteItems")
	private val path: Path
) extends IMenuHandler {
	def select(item: Int) {
	}

	def getOptions: Collection[IShape] = {
		val result = inventory.map{ item =>
			val result = shapeProvider.get()
			result.setShape(
				spriteProvider.get().setSpritePath(
					path.resolve(item.getSpriteName+".png")
				) ,
				"%xx %s".format(inventory.count(item), item.getName))
			result
		}
		return result
	}
}