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
		var inventory = new Inventory()
		"having 2 different items" should{
			inventory.add(pokeball)
			inventory.add(potion)
			"contain both" in{
				assert(inventory.exists(x => x ==pokeball))
				assert(inventory.exists(x => x ==potion))
			}
		}
		"gets another item" should{
			inventory.add(new Item("pokeball"))
			inventory.add(new Item("potion"))
			"still contain both" in{
				assert(inventory.exists(x => x ==pokeball))
				assert(inventory.exists(x => x ==potion))
			}
		}
		"items have been added" should{
			"have an itemcount of 2" in {
				assert(pokeball.getAmount == 2)
				assert(potion.getAmount == 2)
			}
		}
		inventory = new Inventory()
		"a bunch of pokebals have been added and a potion" should{
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
		val item = new Item()
		"having simliar names " should{
			val two = new Item()
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
