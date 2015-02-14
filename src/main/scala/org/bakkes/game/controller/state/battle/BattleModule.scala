package org.bakkes.game.controller.state.battle

import java.util.List

import org.bakkes.game.AModule
import org.bakkes.game.controller.state.battle.contestent.IContestent
import org.bakkes.game.controller.state.battle.contestent.PlayerContestent
import org.bakkes.game.controller.state.battle.contestent.RandyRandom
import org.bakkes.game.model.battle.BattleLogEvent
import org.bakkes.game.model.pokemon.Pokemon

import com.google.inject.Provides
import com.google.inject.Singleton
import com.google.inject.name.Named

import net.codingwell.scalaguice.ScalaModule

class BattleModule extends AModule with ScalaModule{

	override def configure{
		bind[IContestent].to[RandyRandom]
		bind[PlayerContestent].in[Singleton]
	}
    @Provides
	def provideContestent(
			ai:IContestent ,
			player:PlayerContestent ,
            @Named("current players") playerPoke:Pokemon,
            @Named("current enemys") enemyPoke:Pokemon 
        ): Array[IContestent]  = {
        val result : Array[IContestent] = Array(ai,player)
		ai.setOwnPokemon(enemyPoke);
		ai.setTargetPokemon(playerPoke);
		player.setOwnPokemon(playerPoke);
		player.setTargetPokemon(enemyPoke);
		return result;	
	}
    @Provides
	def provideLog(loader : BattleLoader) : List[BattleLogEvent] = {
		return loader.getCurrentLog();
	}


}
