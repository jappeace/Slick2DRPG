package org.bakkes.game.entity.command;

public interface ICommand {

	void execute(float tpf);
	boolean isDone();
	/**
	 * if the command gets cancled before its done
	 */
	void onInterupt();
}
