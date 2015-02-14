package org.bakkes.game.controller.async;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Executor;

import org.bakkes.game.controller.IUpdatable;

/**
 * ## DelayedBatchExeuctor
 * can move code from one thread to another
 * basicly it collects all runnables in a que that are executad. Then all thes tasks
 * are executed when the run method is called
 *
 * one thread adds runnable trough the execute method, the other thread executes these
 * and clears the commands one by one until no more are left
 *
 * ### Example
 * for example the interact dsl runs on its own thread since some commands require
 * the code to be blocked while it waits on user input.
 *
 * however to render (custom) fonts on screen the dialogs have to be drawn on the main thread
 *
 * so I injected a ```@Named("in mainthread") ThreadBridger``` in the dsl and also added this threadbridger
 * to the statemodule updatables.
 *
 * This creates a producer pattern where the dsl keeps adding new commands and the statemodule keeps executing them
 */
public class DelayedBatchExecutor implements IUpdatable, Executor, Runnable{
	private Queue<Runnable> queue = new LinkedList<>();

	/**
	 * the other thread calls this methods which executes all commands once
	 */
	@Override
	public synchronized void update(final int delta) {
		this.run();
	}

	/**
	 * one thread is supposed to add commands via this method
	 * @param r
	 * @return
	 */
	@Override
	public synchronized void execute(final Runnable command) {
        queue.add(command);
	}

	@Override
	public synchronized void run() {
		while(!queue.isEmpty()){
			final Runnable runner = queue.poll();
			runner.run();
		}

	}
}
