package org.bakkes.game;

import com.google.inject.AbstractModule;

/**
 * base module, its good practise to extends this one instead of googles' one
 * this allows me to add functionality to all modules in one go
 *
 * also the name is shorter
 */
public abstract class AModule extends AbstractModule{

	/**
	 * these modules often don't need extra static configuration
	 *
	 * the provider methods are more usefull for me
	 */
	@Override
	protected void configure() {
	}

}
