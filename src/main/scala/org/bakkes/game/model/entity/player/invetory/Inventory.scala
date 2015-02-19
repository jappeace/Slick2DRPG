package org.bakkes.game.model.entity.player.invetory

import org.bakkes.game.model.Bean
import org.newdawn.slick.util.Log

/**
 * a bad inventory repsentation
 *
 * has an aribtrary limit on unqiue items
 */
class Inventory extends Iterable[Item]{

	/**
	 * arbitraty limit on unique items
	 */
	private val inventorySize = 10
	/**
	 * key: the item
	 * value: the amount of the item (in bean to allow changing inside lambdas)
	 */
	private var items = Map[Item, Bean[Int]]()

	/**
	 * count the amount of a certain item is in the inventory
	 * @param item
	 * @return the amount of that item, 0 if there are none (duh)
	 */
	def count(item:Item):Int ={
		items.get(item) match{
			case Some(x) => x.getData()
			case None => 0
		}
	}

	def add(item: Item):Boolean= {
		items.get(item) match{
			case Some (x) => {
				x.setData(x.getData()+1)
				return true
			}
			case None => {
				if(items.size < inventorySize){
					items += item -> new Bean[Int](1)
					return true
				}
				return false
			}
		}
	}
	def remove(item:Item):Boolean = {
		items.get(item) match{
			case Some(x) => {
				if(x.getData() < 1){
					Log.warn("imposible state")
					return false
				}
				if(x.getData() == 1){
					items -= item
					return true
				}
				x.setData(x.getData() -1)
				return true
			}
			case None => return false
		}
	}

	override def iterator: Iterator[Item] = {
		return items.keys.iterator
	}
}