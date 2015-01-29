package org.bakkes.game.controller.async;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * this is a severe reduction of the std interface
 *
 * to much bullshit in there (probably mostly legacy support)
 *
 * also creating a smaller interface should make it easier to create test interfaces for it
 * there is just to much crap in the original I'm not gonna use (even tough I will use their implementation)
 *
 * creating my own interface reduces the overal code
 */
public interface IThreadPool {

	/**
	 * kill the pool
	 */
    void shutdown();

    /**
     * do this and gib me something back
     * @param command
     */
    <T> Future<T> submit(Callable<T> task);

    /**
     * just do this whenever
     * @param command
     */
    void execute(Runnable command);
}
