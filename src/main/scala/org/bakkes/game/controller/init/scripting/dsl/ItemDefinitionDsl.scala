package org.bakkes.game.controller.init.scripting.dsl

import org.bakkes.game.model.IInteractable
import org.bakkes.game.model.entity.player.invetory.Item

/**
 * to keeps things consistent a item (with a name) should always do the same thing
 * a npc can do different things, (because you know npc's can make choices, items are things and cannot).
 *
 * that is why items are defined outside the area and npc's inside each area
 */
class ItemDefinitionDsl extends AInteractableDsl {
	private var result: Item = new Item

	def getItem: Item = {
		return result
	}

	def setItemName(itemName: String) {
		result.setName(itemName)
	}

	protected def getTarget: Item = {
		return result
	}

	protected def getInteractTarget: IInteractable = {
		return getTarget
	}
}
case class poep(var name:String)
object a{
	val b = new poep("b")
	b.name = "a"
}
