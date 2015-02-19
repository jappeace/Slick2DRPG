package test.org.bakkes.game

import org.bakkes.game.model.entity.player.invetory.{Inventory, Item}
import org.scalatest.WordSpec

/**
 * this class test case class functionality which is new to me
 *
 * so it serves as a demonstration as an experiment
 */
class InventorySpec extends WordSpec{

	val pokeball = new Item("pokeball")
	val potion = new Item("potion")

	"an inventory" when{
		"having 2 different items" should{
			val inventory = new Inventory()
			inventory.add(pokeball)
			inventory.add(potion)
			"contain both" in{
				assert(inventory.exists(x => x ==pokeball))
				assert(inventory.exists(x => x ==potion))
			}
		}
		"gets another item" should{
			val inventory = new Inventory()
			inventory.add(pokeball)
			inventory.add(potion)
			inventory.add(new Item("pokeball"))
			inventory.add(new Item("potion"))
			"still contain both" in{
				assert(inventory.exists(x => x ==pokeball))
				assert(inventory.exists(x => x ==potion))
			}
		}
		"items have been added" should{
			val inventory = new Inventory()
			inventory.add(pokeball)
			inventory.add(potion)
			inventory.add(new Item("pokeball"))
			inventory.add(new Item("potion"))
			"have an itemcount of 2" in {
				assert(inventory.count(pokeball) == 2)
				assert(inventory.count(potion) == 2)
			}
		}
		"a bunch of pokebals have been added and a potion" should{
			val inventory = new Inventory()
			inventory.add(new Item("pokeball"))
			inventory.add(new Item("pokeball"))
			inventory.add(new Item("pokeball"))
			inventory.add(new Item("pokeball"))
			inventory.add(new Item("potion"))
			"still contain both" in{
				assert(inventory.exists(x => x ==pokeball))
				assert(inventory.exists(x => x ==potion))
			}
		}
	}
	"An item " when{
		val item = new Item("name")
		"having simliar names " should{
			val two = new Item("name")
			"be equal " in {
				assert(item == two)
			}
		}
		"having different names" should{
			"not be equal " in {
				assert(pokeball != potion)
			}
		}
		"having been mutated" should{
			"still equal a new one with the same name " in{
				assert(pokeball == new Item("pokeball"))
			}
		}
	}
}
