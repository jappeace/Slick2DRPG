package test.org.bakkes.game

import org.bakkes.game.model.entity.player.invetory.Item
import org.scalatest.WordSpec

/**
 * this class test case class functionality which is new to me
 *
 * so it serves as a demonstration as an experiment
 */
class ItemSpec extends WordSpec{

	"An item " when{
		"having simliar names " should{
			val one = new Item()
			val two = new Item()
			"be equal " in {

			}
		}
	}
}
