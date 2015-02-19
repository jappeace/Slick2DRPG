package org.bakkes.game.model

class ASpriteNamedModel() extends AModel() with IHasSpriteName {
	/**
	 * overwrite the regular name for a spritname,
	 * By convention use a regular name, it makes things more readable.
	 *
	 * all the pokemon are named by a number instead of a sprite, renaming them all
	 * is a bit of a choir, so for now I've implemented thsi feature
	 *
	 * Using this for example to rename a npc whilest having a different skin is a bad idea.
	 * Instead you should create a symlink (shortcut) to the desired animation so he will always have the
	 * same skin
	 */
	private var spriteName: String = ""

	/**
	 * views should call this method instead of calling getName,
	 * getName is for showing the name as a string, this has some
	 * extra filesystem friendly modifications like killing spaces
	 */
	def getSpriteName: String = {
		if (spriteName.isEmpty) {
			spriteName = getName.toLowerCase.replace(' ', '_')
		}
		return spriteName
	}

	def setSpriteName(to: String) {
		assert(to != null)
		spriteName = to
	}
}