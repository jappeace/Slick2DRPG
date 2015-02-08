package org.bakkes.game.controller.state.overworld.command;

/**
 * if the command has to happen only once, extend this class
 */
public abstract class AOneTimeCommand extends ACommand{

	@Override
	public final void execute(final float tpf) {
		executeOnce(tpf);
		done();
	}

	public abstract void executeOnce(float tpf);

}
