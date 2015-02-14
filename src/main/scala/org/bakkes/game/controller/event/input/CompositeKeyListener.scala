package org.bakkes.game.controller.event.input

import java.util.Collection
import scala.collection.JavaConversions._

import com.google.inject.Inject

class CompositeKeyListener @Inject() (listeners : Collection[IKeyListener]) extends IKeyListener{

	override def KeyDown(key:Key) = {
		listeners.foreach{_.KeyDown(key)}
	}

	override def KeyUp(key:Key) = {
		listeners.foreach{_.KeyUp(key)}
	}

	def clear() = {
		listeners.clear();
	}
	def add(listner:IKeyListener) : Boolean = {
		return listeners.add(listner);
	}
}
