package org.bakkes.game.controller.state.battle;

import com.google.inject.name.{Named, Names}
import com.google.inject.{Provider, Provides, Singleton}
import net.codingwell.scalaguice.ScalaModule
import org.bakkes.game.AModule
import org.bakkes.game.controller.MenuController
import org.bakkes.game.controller.state.event.input.CompositeKeyListener
import org.bakkes.game.controller.state.battle.event.BattleMenuHandler
import org.bakkes.game.controller.state.battle.event.key.BattleEndListner
import org.bakkes.game.model.pokemon.Pokemon
import org.bakkes.game.view.components.{ITextableShape, Menu}

/**
 * bridging module
 */
class BattleStateModule extends AModule with ScalaModule{
	override def configure{
    	//bind(Menu.class).annotatedWith(Names.named("moves")).to(Menu.class).in(Singleton.class);
    	bind[BattleLoader].in[Singleton]
    	bind[Menu].annotatedWith(Names.named("moves")).to[Menu].in[Singleton]
    }

    @Provides
    @Named("current players")
    def provideCurrentPlayerPokemon (loader:BattleLoader) : Pokemon = loader.getCurrentPlayerPokemon();

    @Provides
    @Named("current enemys")
    def provideCurrentEnemyPokemon (loader:BattleLoader) : Pokemon = loader.getCurrentEnemyPokemon();
	@Provides
	@Named("battle")
	def provideBattleMenu(result:Menu, lineProvider:Provider[ITextableShape]) : Menu = {
		return result
	}

    @Provides
    @Named("battle")
    def provideMenuController(
    		@Named("moves") moveMenu:Menu, 
    		result:MenuController, 
    		handler:BattleMenuHandler
    ) : MenuController = {
    	moveMenu.clear()
    	result.set(moveMenu,handler)
    	return result
    }

    @Provides
    @Named("battle")
    def provideKeyInput(
    	result:CompositeKeyListener,
    	end:BattleEndListner,
    	@Named("battle") controller:MenuController
    ):CompositeKeyListener = {
    	result.clear()
    	result.add(end)
    	result.add(controller)
    	return result
    }
    @Provides
    @Named("current")
    def provideBattle(loader:BattleLoader) : Battle = loader.getCurrentBattle();
}
