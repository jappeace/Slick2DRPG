package org.bakkes.game.controller.state.battle;

import org.bakkes.game.AModule;
import org.bakkes.game.controller.MenuController;
import org.bakkes.game.controller.event.BattleMenuHandler;
import org.bakkes.game.controller.event.input.BattleEndListner;
import org.bakkes.game.controller.event.input.CompositeKeyListener;
import org.bakkes.game.model.pokemon.Pokemon;
import org.bakkes.game.view.components.Menu;

import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.google.inject.name.Names;

/**
 * bridging module
 */
public class BattleStateModule extends AModule{
    @Override
	public void configure(){
    	//bind(Menu.class).annotatedWith(Names.named("moves")).to(Menu.class).in(Singleton.class);
    	bind(BattleLoader.class).in(Singleton.class);
    	bind(Menu.class).annotatedWith(Names.named("moves")).to(Menu.class).in(Singleton.class);
    }

    @Provides
    @Named("current players")
    public  Pokemon provideCurrentPlayerPokemon(final BattleLoader loader){
    	return loader.getCurrentPlayerPokemon();
    }

    @Provides
    @Named("current enemys")
    public  Pokemon provideCurrentEnemyPokemon(final BattleLoader loader){
    	return loader.getCurrentEnemyPokemon();
    }

    @Provides
    @Named("battle")
    public MenuController provideMenuController(@Named("moves") final Menu moveMenu, final MenuController result, final BattleMenuHandler handler){
    	moveMenu.clear();
    	result.set(moveMenu, handler);
    	return result;
    }

    @Provides
    @Named("battle")
    public CompositeKeyListener provideKeyInput(
    		final CompositeKeyListener result,
    		final BattleEndListner end,
    		@Named("battle") final MenuController controller
        ){
    	result.clear();
    	result.add(end);
    	result.add(controller);
    	return result;
    }

    @Provides
    @Named("current")
    public Battle provideBattle(final BattleLoader loader){
    	return loader.getCurrentBattle();
    }
}
