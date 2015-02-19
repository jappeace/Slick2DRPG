package org.bakkes.game.controller.init.scripting.dsl

import org.bakkes.game.model.entity.IInteractable
import org.bakkes.game.model.entity.player.invetory.Item

/**
 * to keeps things consistent a item (with a name) should always do the same thing
 * a npc can do different things, (because you know npc's can make choices, items are things and cannot).
 *
 * that is why items are defined outside the area and npc's inside each area
 */
class ItemDefinitionDsl extends AInteractableDsl {
	var result : Option[Item] = None
	def createItem: Item = getTarget

	def setItemName(to: String) {
		result match{
			case Some(x) => x.setName(to)
			case None => result = Some[Item](new Item(to))
		}
	}

	protected def getTarget: Item = {
		result match{
			case Some(x) => x
			case None => throw new IllegalArgumentException("item not created, set name first")
		}
	}

	protected def getInteractTarget: IInteractable = {
		return getTarget
	}
}
