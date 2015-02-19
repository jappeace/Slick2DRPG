
package test.org.bakkes.game

import com.google.inject.Guice
import net.codingwell.scalaguice.InjectorExtensions._
import org.bakkes.game.model.map.Direction
import org.bakkes.game.view.components.{AShape, ShapeComposition}
import org.newdawn.slick.Graphics
import org.scalatest.WordSpec

class ShapeCompositionSpec extends WordSpec {

	class TestShape extends AShape {
		val dimension = 5

		def height(): Float = dimension

		def width(): Float = dimension

		def render(g: Graphics) {}
	}

	"A ShapeComposition" when {
		"Consisting of a testshape" should {
			"have a dimension height" in new mainShape {
				assert(shape.height() == testShape.dimension)
			}
			"have a dimension width" in new mainShape {
				assert(shape.width() == testShape.dimension)
			}
		}

		"Consisting of 2 vertical testshapes" should {
			"have double height" in new northAndMainShape {
				assert(shape.height() == testShape.dimension * 2)
			}
			"have normal width" in new northAndMainShape {
				assert(shape.width() == testShape.dimension)
			}
		}
		"Consisting of 3 vertical testshapes" should {
			"have tripple height" in new northSouthAndMainShape {
				assert(shape.height() == testShape.dimension * 3)
			}
			"have normal width" in new northSouthAndMainShape {
				assert(shape.width() == testShape.dimension)
			}
		}
		"Consisting all possible testshapes" should {
			"have tripple height" in new allShapes {
				assert(shape.height() == testShape.dimension * 3)
			}
			"have tripple width" in new allShapes {
				assert(shape.width() == testShape.dimension * 3)
			}
		}
	}

	trait composition {
		def shape: ShapeComposition = Guice.createInjector().instance[ShapeComposition]
	}

	trait mainShape extends composition {
		def testShape: TestShape = new TestShape()

		override def shape: ShapeComposition = {
			val result = super.shape
			result.setShape(testShape)
			return result
		}
	}

	trait northAndMainShape extends mainShape {
		override def shape: ShapeComposition = {
			val result = super.shape
			result.put(Direction.North, testShape)
			return result
		}
	}

	trait northSouthAndMainShape extends northAndMainShape {
		override def shape: ShapeComposition = {
			val result = super.shape
			result.put(Direction.South, testShape)
			return result
		}
	}

	trait allShapes extends northSouthAndMainShape {
		override def shape: ShapeComposition = {
			val result = super.shape
			result.put(Direction.East, testShape)
			result.put(Direction.West, testShape)
			return result
		}
	}

}
