package org.bakkes.game.controller.state.battle;

import org.bakkes.game.PathsModule;
import org.bakkes.game.controller.async.IThreadPool;
import org.bakkes.game.controller.event.input.CompositeKeyListener;
import org.bakkes.game.controller.state.CommonGameState;
import org.bakkes.game.controller.state.State;
import org.bakkes.game.controller.state.battle.contestent.PlayerContestent;
import org.bakkes.game.model.entity.player.invetory.PokeBelt;
import org.bakkes.game.model.font.FontModule;
import org.bakkes.game.model.pokemon.Pokemon;
import org.bakkes.game.view.ViewModule;
import org.bakkes.game.view.battle.BattleView;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provider;
import com.google.inject.name.Named;

public class BattleState extends CommonGameState {

	private Battle battle;
	private @Inject PlayerContestent playerContestent;
	private @Inject IThreadPool pool;
	private @Inject @Named("from player") PokeBelt player;
	private IBattleLoader loader;
	private @Inject Provider<WildLoader> wildLoader;
	private @Inject Provider<BattleStateModule> moduleProvider;


	@Override
	public void enter() {
		final Pokemon enemy = loader.getEnemy();
		final Pokemon player = this.player.getFirstAlive();
		playerContestent.setOwnPokemon(player);
		playerContestent.setTargetPokemon(enemy);
		final Injector injector = Guice.createInjector(
            new BattleModule(playerContestent),
            moduleProvider.get(),
            new FontModule(),
            new PathsModule(),
            new ViewModule()
        );
		this.battle = injector.getInstance(Battle.class);
		this.addRenderable(injector.getInstance(BattleView.class));
		this.setKeyListener(injector.getInstance(CompositeKeyListener.class));
		pool.execute(this.battle);
	}

	public void setType(final BattleType to){
		if(to == BattleType.Wild){
			loader = wildLoader.get();
		}
	}

	@Override
	public int getID() {
		return State.Battle.ordinal();
	}
}
