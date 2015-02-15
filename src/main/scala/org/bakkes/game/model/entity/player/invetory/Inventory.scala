package org.bakkes.game.model.entity.player.invetory

import scala.collection.mutable.ArrayBuffer

class Inventory extends Iterable[Item]{

	private val inventorySize = 10
	private val items = new ArrayBuffer[Item](inventorySize)

	def add(item: Item):Boolean= {
		val optional = items.find{_.getName == item.getName}
		if(optional.nonEmpty){
			optional.get.amount = optional.get.amount + 1
			return true
		}
		if (items.size < inventorySize ) {
			item.inventory_=(this)
			items += item
			return true
		}
		return false
	}
	def remove(item:Item):Boolean = {
		val index = items.indexOf(item)
		if(index == -1){
			return false
		}
		items.remove(index)
		return true
	}

	override def iterator: Iterator[Item] = {
		return items.iterator
	}
}