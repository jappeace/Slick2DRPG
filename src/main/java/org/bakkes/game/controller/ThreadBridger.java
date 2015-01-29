package org.bakkes.game.controller;

import java.util.LinkedList;
import java.util.Queue;

/**
 * ## Thread bridger
 * can move code from one thread to another
 *
 * one thread adds runnable trough the add method, the other thread executes these
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
public class ThreadBridger implements IUpdatable{
	private Queue<IUpdatable> queue = new LinkedList<>();

	/**
	 * one thread is supposed to add commands via this method
	 * @param r
	 * @return
	 */
	public synchronized boolean add(final IUpdatable r){
        return queue.add(r);
	}

	/**
	 * the other thread calls this methods which executes all commands once
	 */
	@Override
	public synchronized void update(final int delta) {
		while(!queue.isEmpty()){
			final IUpdatable runner = queue.poll();
			runner.update(delta);
		}
	}
}
