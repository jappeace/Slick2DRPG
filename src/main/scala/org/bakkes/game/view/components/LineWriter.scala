package org.bakkes.game.view.components

import com.google.inject.{Inject, Provider}
import org.bakkes.game.view.IRenderable
import org.newdawn.slick.{Color, Font, Graphics}

import scala.collection.immutable.List

/**
 * works like a type writer for shape, it will go down everytime a shape is added
 * by the height of it, originaly intended only for strings, but after observation
 * it works for anything with the dimensions of a shape
 *
 * don't extends a view otherwise stackoverflow
 *
 * a simple utility to emulate the cout << "" kind of stuff on screen
 */
class LineWriter @Inject() (
		private var textLineProvider: Provider[ITextableShape]
						 )extends AShape with IRenderable {
	private var lines = List[IShape]()
	var color: Color = Color.black

	def render(g: Graphics) {
		g.setColor(color)
		for (l <- lines) {
			l.render(g)
		}
	}

	def width: Float = {
		var result = 0f
		val max = lines.sortWith(
			(a, b) => a.x() + a.width() < b.x() + b.width()
		).head
		val min = lines.sortBy((x) => x.x()).last
		result = max.x - min.x
		return result
	}

	def height: Float = {
		val sorted = lines.sortBy((y) => y.y())
		return sorted.last.y() - sorted.head.y()
	}

	def write(str: String) {
		write(str, null)
	}

	def write(str: String, font: Font) {
		val t = textLineProvider.get
		t.setText(str)
		t.y(y)
		t.x(x)
		t.setFont(font)
		write(t)
	}

	def write(line: IShape) {
		lines =  line :: lines
		y(y + line.height)
	}

	def clear = lines = List[IShape]()
	def lineCount: Int = lines.count((x)=>true)
}