package org.bakkes.game.model.entity.player.invetory

import com.google.inject.Inject
import nl.jappieklooster.annotation.Nullable
import org.bakkes.game.model.entity.OverworldEntity
import org.newdawn.slick.util.Log

class  Item  (private var _amount:Int) extends OverworldEntity{
	@Inject()
	def this() = this(1)
	@Nullable
	private var _inventory:Inventory = null

	def amount = _amount
	def amount_=(to:Int) : Unit = {_amount = to}

	def inventory_=(to:Inventory) : Unit = {
		if(_inventory != null){
			Log.warn("inventory was not empty")
		}
		_inventory = to
	}
}

