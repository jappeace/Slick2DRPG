package org.bakkes.game.model.entity

import groovy.lang.Closure
import org.newdawn.slick.util.Log

trait TInteractor extends IInteractable{
	private var onInteract : Option[Closure[Void]] = None

	override def interact {
		Log.info("interacting with " + getName)
		onInteract match{
			case Some(x) => x.call
			case None => Log.info("no interaction present")
		}
	}

	override def setInteract(callback: Closure[Void]) {
		onInteract = Some(callback)
	}
}
