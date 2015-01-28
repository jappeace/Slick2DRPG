package org.bakkes.game.controller.scripting.dsl.area;

import groovy.lang.Closure;

import org.bakkes.game.controller.scripting.dsl.ADsl;
import org.bakkes.game.model.IModel;
import org.bakkes.game.view.overworld.dialog.Dialog;
import org.bakkes.game.view.overworld.dialog.IMessageBox;
import org.bakkes.game.view.overworld.dialog.MessageBox;
import org.bakkes.game.view.overworld.dialog.MessageBoxState;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class InteractDsl extends ADsl {
	private @Inject Provider<MessageBox> messageBoxProvider;
	private @Inject Provider<Dialog> dialogProvider;
	private @Inject Provider<PlayerDsl> playerDslProvider;
	public IModel target;

	/**
	 * show a dialog
	 * @param text
	 * @return
	 */
	public IMessageBox dialog(final String text){
		return dialog(text, target.getName() + ":");
	}

	public IMessageBox dialog(final String text, final String title){
		final IMessageBox dialog = messageBoxProvider.get();
		dialog.setTitle(title);
		dialog.setText(text);
		dialog.setState(MessageBoxState.Queued);
		return dialog;
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

	public boolean decision(final String text){
		return decision(text, "decision:");
	}
	public boolean decision(final String text, final String title){
		final Dialog d = dialogProvider.get();
		d.setText(text);
		d.setTitle(title);
		d.show();
		return false;
	}
}
