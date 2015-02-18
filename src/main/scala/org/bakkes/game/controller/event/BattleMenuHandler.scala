package org.bakkes.game.controller.event

import java.util.Collection
import java.util.LinkedList

import org.bakkes.game.controller.init.scripting.loader.ItemLoader
import org.bakkes.game.controller.state.battle.contestent.PlayerContestent
import org.bakkes.game.model.AModel
import org.bakkes.game.model.battle.move.IMove
import org.bakkes.game.model.entity.player.invetory._
import org.bakkes.game.model.pokemon.Pokemon
import org.bakkes.game.view.components.IShape
import org.bakkes.game.view.components.ITextableShape
import org.newdawn.slick.util.Log

import com.google.inject.Inject
import com.google.inject.Provider
import com.google.inject.name.Named
import scala.collection.JavaConversions._
import scala.util.Random

class BattleMenuHandler @Inject() (
	@Named("current players") pokemon : Provider[Pokemon],
	@Named("current enemys") enemyPokemon: Provider[Pokemon],
	@Named("from player") pokebelt:PokeBelt,
	@Named("from player") inventory:Inventory,
	lines : Provider[ITextableShape],
	contestent : PlayerContestent,
	itemLoader:ItemLoader,
	random:Random
) extends IMenuHandler{

	override def select(item:Int){
		if(contestent.selectMove(item)){
			return
		}
		throwPokeball(itemLoader.load("pokeball"))
	}
	private def throwPokeball(item:Item): Unit ={
		if(!inventory.contains(item)){
			Log.info("no pokeballs, show user message")
			return
		}
		inventory.remove(item)
		val catchChance = 0.8
		val enemy = enemyPokemon.get()
		if (random.nextFloat() < catchChance){
			Log.info("you caught a " + enemy.getName)
			pokebelt.add(enemy)
			enemy.damage(enemy.getCurrentStats.getHealth)
		}

	}
	override def getOptions : Collection[IShape] = {
		val result = pokemon.get().getMoves().map{move =>
			toLine(move.getName())
		}
		result.add(toLine("pokeball"))
		return result
	}

	private def toLine(input:String):ITextableShape = lines.get().setText(input)


}