package org.bakkes.game.controller.battle.contestent;

import org.bakkes.game.model.pokemon.Pokemon;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;

public class ContestentModule extends AbstractModule{

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

	public void flip(){
		final Pokemon temp = own;
		own = target;
		target = temp;
	}


}
