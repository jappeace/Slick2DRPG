package org.bakkes.game.controller.command;

/**
 * if the command has to happen only once, extend this class
 */
public abstract class AOneTimeCommand extends ACommand{

	@Override
	public final void execute(final float tpf) {
		executeOnce(tpf);
		done();
	}

	abstract void executeOnce(float tpf);

}
