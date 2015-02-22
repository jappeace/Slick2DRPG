package org.bakkes.game.view

import java.nio.file.Path

import com.google.inject.Inject
import com.google.inject.name.Named
import org.bakkes.game.model.pokemon.Pokemon
import org.bakkes.game.view.components.{AShape, Sprite}
import org.bakkes.game.view.SpriteType._
import org.newdawn.slick.Graphics
import org.newdawn.slick.geom.Vector2f

class PokemonSprite @Inject() (
	sprite:Sprite,
	@Named("spritePokemon")
	path:Path
) extends AShape{
	var spriteType = small
	val imgExtension = ".png"
	def setPokemon(to:Pokemon){
		val spriteFolderPath = path.resolve(to.name)

		var shiny = ""
		var female = ""
		to match {
			case isShiny => shiny = "shiny_"
			case isFemale => female = "_female"
		}
		val prefferedPath = spriteFolderPath.resolve(
				shiny + spriteType.name() + female + imgExtension
			)
		if(prefferedPath.toFile.exists){
			sprite.setSpritePath(prefferedPath)
		}else{
			sprite.setSpritePath(
				spriteFolderPath.resolve(
					spriteType.name() + imgExtension
				)
			)
		}
	}
	override def width(): Float = sprite.width
	override def height(): Float = sprite.height
	override def render(g: Graphics): Unit = sprite.render(g)
	override def onChangePosition(to:Vector2f){
		sprite.setPosition(to)
	}
}
