package org.bakkes.game.controller.command;

public abstract class ACommand implements ICommand{
	private boolean isDone = false;
	private boolean interupted = false;

	@Override
	public final boolean isDone() {
		// TODO Auto-generated method stub
		return isDone;
	}


	@Override
	public void onInterupt() {
		interupted = true;
	}

	/**
	 * call it when this command is finished and should be removed from the queu to
	 * allow the next command to take place
	 */
	protected void done(){
		isDone = true;
	}

	/**
	 * check if a interupt has happend (user pressed spacebar)
	 * it is the commands' responsibility to clean up the logic ASAP after an interupt and call done
	 * @return
	 */
	protected boolean isInterupted(){
		return interupted;
	}
}
