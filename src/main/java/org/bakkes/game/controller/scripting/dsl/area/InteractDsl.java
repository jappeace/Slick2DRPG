package org.bakkes.game.controller.scripting.dsl.area;

import groovy.lang.Closure;

import org.bakkes.game.controller.scripting.dsl.ADsl;
import org.bakkes.game.model.IModel;
import org.bakkes.game.view.overworld.DialogBox;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class InteractDsl extends ADsl {
	private @Inject Provider<DialogBox> dialogProvider;
	private @Inject Provider<PlayerDsl> playerDslProvider;
	public IModel target;

	/**
	 * show a dialog
	 * @param text
	 * @return
	 */
	public DialogBox dialog(final String text){
		return dialog(text, target.getName() + ":");
	}

	public DialogBox dialog(final String text, final String title){
		final DialogBox dialog = dialogProvider.get();
		dialog.setTitle(title);
		dialog.setText(text);
		dialog.show();
		return dialog;
	}

	/**
	 * do stuff to the player
	 * @param commands
	 */
	public void selectPlayer(final Closure commands){
		delegate(commands, playerDslProvider.get());
	}

	/**
	 * show a tought
	 * @param text
	 */
	public void tought(final String text){
        dialog(text, "you think:");
	}
}
