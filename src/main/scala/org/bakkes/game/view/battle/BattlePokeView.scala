package org.bakkes.game.view.battle;

import java.nio.file.Path

import com.google.inject.Inject
import com.google.inject.name.Named
import org.bakkes.game.model.GameInfo
import org.bakkes.game.model.map.Direction
import org.bakkes.game.model.pokemon.Pokemon
import org.bakkes.game.view.SpriteType
import org.bakkes.game.view.components.{AShape, ShapeComposition, Sprite}
import org.newdawn.slick.Graphics
import org.newdawn.slick.geom.Vector2f;

class BattlePokeView @Inject() (
   val avatar:Sprite,
   val statsView:StatisticsView,
   view:ShapeComposition,
   @Named("spritePokemon") path:Path
) extends AShape{

	def setPokemon(to:Pokemon, spriteType:SpriteType, avatarDir:Direction){
		avatar.setSpritePath(path.resolve(spriteType.getImageName(to)))
		statsView.setPokemon(to)
		val margin = 40
		statsView.x(GameInfo.SCREEN_WIDTH - statsView.width() - margin)
		statsView.y(margin)
		view.setShape(statsView)
		view.put(avatarDir, avatar)
		x(x()); // update position of subshapes
	}

	override def render (g:Graphics) = view.render(g)
	override def onChangePosition(position:Vector2f) = view.setPosition(position)
	override def width() = view.width()
	override def height() = view.height()
}
