package org.bakkes.game.controller.state.battle.contestent;

import org.bakkes.game.AModule;
import org.bakkes.game.model.pokemon.Pokemon;

import com.google.inject.Provides;
import com.google.inject.name.Named;

public class ContestentModule extends AModule{

	private Pokemon own;
	private Pokemon target;

	public ContestentModule(final Pokemon own, final Pokemon target){
		this.own = own;
		this.target = target;
	}
	public @Named("own") @Provides Pokemon provideOwn(){
		return own;
	}
	public @Named("target") @Provides Pokemon provideTarget(){
		return target;
	}
	@Override
	protected void configure() {
		/**
		 * what AI to use by default
		 */
		bind(IContestent.class).to(RandyRandom.class);
	}
}