package org.bakkes.game.controller.scripting.dsl.area;

import groovy.lang.Closure;

import org.bakkes.game.controller.MessageBoxController;
import org.bakkes.game.controller.scripting.dsl.ADsl;
import org.bakkes.game.model.GameInfo;
import org.bakkes.game.model.IModel;
import org.bakkes.game.view.overworld.dialog.Dialog;
import org.bakkes.game.view.overworld.dialog.MessageBox;
import org.bakkes.game.view.overworld.dialog.MessageBoxState;
import org.newdawn.slick.util.Log;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class InteractDsl extends ADsl {
	private @Inject Provider<MessageBox> messageBoxProvider;
	private @Inject Provider<Dialog> dialogProvider;
	private @Inject Provider<PlayerDsl> playerDslProvider;
	private @Inject MessageBoxController msgBoxController;
	public IModel target;

	/**
	 * show a dialog
	 * @param text
	 * @return
	 */
	public void dialog(final String text){
		dialog(text, target.getName() + ":");
	}

	public void dialog(final String text, final String title){
		msgBoxController.add(title,text);
	}

	/**
	 * do stuff to the player
	 * @param commands
	 */
	public void selectPlayer(final Closure<Void> commands){
		delegate(commands, playerDslProvider.get());
	}

	/**
	 * show a tought
	 * @param text
	 */
	public void tought(final String text){
        dialog(text, "you think:");
	}

	public int decision(final String text){
		return decision(text, "decision:");
	}
	public int decision(final String text, final String title){
		final Dialog dialog = dialogProvider.get();
		dialog.setText(text);
		dialog.setTitle(title);
		msgBoxController.add(dialog);
		while(dialog.getState() != MessageBoxState.Done){
			try {
				Thread.sleep(GameInfo.USERINPUT_THREADSLEEP_TIME);
			} catch (final InterruptedException e) {
				Log.info("ooh nos, I have really no Idea what this means");
			}
		}
		final int choice = dialog.getSelected();
		Log.info("chose option: "+ choice);
		return choice ;
	}
}
