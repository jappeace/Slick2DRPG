package org.bakkes.game.model

/**
 * a trait to add stuff before and after an existing string
 *
 * for example in item class you want to have the name of the item but also sometimes the amount
 * so you can override the getName method with this class to add the ammount
 *
 * use with caution
 */
trait TStringExtender{

	var before:Option[String] = None
	var after:Option[String] = None
	def target:String
	def extend:String = {
		var result = target
		before match{
			case Some(x) => result = x + result
			case None =>
		}
		after match{
			case Some(x) => result = result + x
			case None =>
		}
		return result
	}
}

