package org.bakkes.game.model

/**
 * pokemon have depending on the situation big, small, front or back images (not to speak of the shiny ones)
 * this class allows the views to pick the right ones
 */
class SpriteNameExtender extends ModelNameExtender with IHasSpriteName {
	private var spriteNamed: IHasSpriteName = null
	val spriteNameExtender = new TStringExtender(){
		override def target = spriteNamed.getSpriteName
	}
	def setHasSpriteName(spriteName: IHasSpriteName) {
		spriteNamed = spriteName
		setModel(spriteNamed)
	}

	override def getSpriteName:String = spriteNameExtender.extend
	override def setSpriteName(to: String) = spriteNamed.setSpriteName(to)
}