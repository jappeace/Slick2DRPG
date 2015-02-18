package org.bakkes.game.controller.event

import java.nio.file.Path
import java.util.Collection
import java.util.LinkedList
import org.bakkes.game.model.{SpriteNameExtender, ModelNameExtender}
import org.bakkes.game.model.entity.player.invetory.Inventory
import org.bakkes.game.model.entity.player.invetory.Item
import org.bakkes.game.view.components.IShape
import org.bakkes.game.view.overworld.SpritedNameView
import com.google.inject.Inject
import com.google.inject.Provider
import com.google.inject.name.Named
import scala.collection.JavaConversions._

class ItemMenuHandler @Inject() (
	@Named("from player")
	private val inventory: Inventory,
	private val viewsProvider: Provider[SpritedNameView],
	private val nameExtenderProvider : Provider[SpriteNameExtender],
	@Named("spriteItems")
	private val path: Path
) extends IMenuHandler {
	def select(item: Int) {
	}

	def getOptions: Collection[IShape] = {
		val result = inventory.map{ item =>
			val itemNamed = nameExtenderProvider.get()
			itemNamed.setHasSpriteName(item)
			itemNamed.nameExtender.before = Option(item.amount.toString)
			val result = viewsProvider.get()
			result.setNamed(path, itemNamed)
			result
		}
		return result
	}
}