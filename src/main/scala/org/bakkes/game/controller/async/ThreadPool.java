package org.bakkes.game.controller.async;

import com.google.inject.Inject;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class ThreadPool implements IThreadPool{
	private @Inject ExecutorService pool;

	@Override
	public void shutdown() {
		pool.shutdown();
	}

	@Override
	public <T> Future<T> submit(final Callable<T> task) {
		return pool.submit(task);
	}

	@Override
	public void execute(final Runnable command) {
		pool.execute(command);
	}
}
