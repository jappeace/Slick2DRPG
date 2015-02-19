package org.bakkes.game.model.entity.player

import com.google.inject.Inject
import com.google.inject.Provider
import org.bakkes.game.controller.state.overworld.command.ICommand
import org.bakkes.game.controller.state.overworld.command.WalkPath
import org.bakkes.game.model.entity.Character
import org.bakkes.game.model.entity.player.invetory.Inventory
import org.bakkes.game.model.entity.player.invetory.PokeBelt
import org.bakkes.game.model.map.Tile

import scala.beans.BeanProperty

class Player @Inject() (
   @BeanProperty
	val pokebelt: PokeBelt,
   @BeanProperty
	val inventory: Inventory,
	private val walkPathProvider: Provider[WalkPath]
) extends Character {
	private var commands = List[ICommand]()
	private var _hasEnteredNewTile: Boolean = false

	setName("player")

	def addCommand(command: ICommand) {
		// operator overloading is confusing as shit
		commands =  commands ::: List[ICommand](command)
	}

	def moveTo(tile: Tile) {
		val command = walkPathProvider.get
		command.setDestination(tile)
		addCommand(command)
	}

	def update(delta: Int) {
		if (isDone) {
			return
		}
		if (commands.head.isDone) {
			commands = commands.tail
		}
		if (isDone) {
			return
		}
		commands.head.execute(delta / 10f)
	}

	/**
	 * gives the current command an interupt command and removes all others from
	 * the execution queue
	 */
	def clearCommands {
		if (!isDone) {
			val first: ICommand = commands.head
			first.onInterupt
			commands = List[ICommand](first)
		}
	}

	override def onEnterNewTile {
		_hasEnteredNewTile = true
		super.onEnterNewTile
	}

	def hasEnteredNewTile: Boolean = {
		val result: Boolean = _hasEnteredNewTile
		_hasEnteredNewTile = false
		return result
	}
	def isDone: Boolean = commands.isEmpty
}