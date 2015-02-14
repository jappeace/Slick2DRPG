package org.bakkes.game.controller.async;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.bakkes.game.AModule;

import com.google.inject.Provides;
import com.google.inject.Singleton;

/**
 * this module is very dangerous
 *
 * its funny cause its true, and a pun
 */
public class ThreadModule extends AModule{

	@Override
	public void configure(){
		bind(IThreadPool.class).to(ThreadPool.class).in(Singleton.class);;
	}

	public @Provides ExecutorService provideThreadPool(){
		return Executors.newCachedThreadPool();
	}
}
