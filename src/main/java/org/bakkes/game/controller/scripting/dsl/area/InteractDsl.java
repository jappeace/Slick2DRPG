package org.bakkes.game.controller.scripting.dsl.area;

import groovy.lang.Closure;

import org.bakkes.game.controller.IUpdatable;
import org.bakkes.game.controller.MessageBoxController;
import org.bakkes.game.controller.ThreadBridger;
import org.bakkes.game.controller.scripting.dsl.ADsl;
import org.bakkes.game.model.GameInfo;
import org.bakkes.game.model.IModel;
import org.bakkes.game.view.overworld.dialog.Dialog;
import org.bakkes.game.view.overworld.dialog.MessageBoxState;
import org.newdawn.slick.util.Log;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;
import com.sun.istack.internal.Nullable;

public class InteractDsl extends ADsl {
	private @Inject Provider<Dialog> dialogProvider;
	private @Inject Provider<PlayerDsl> playerDslProvider;
	private @Inject MessageBoxController msgBoxController;
	private @Inject @Named("in mainthread") ThreadBridger threadBridger;
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
		threadBridger.add(new IUpdatable(){
            @Override
            public void update(final int delta) {
                msgBoxController.add(title,text);
            }
        });
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

		final DialogConstructor d = new DialogConstructor(text, title);

		threadBridger.add(d);
		// now the wait begins
		// first till the fucking thing is constructed
		while(d.getDialog() == null){
			sleep();
		}
		final Dialog dialog = d.getDialog();

		// then till the user has inputted their wishes
		while(dialog.getState() != MessageBoxState.Done){
			sleep();
		}

		// finaly done (a small eternity passed for the pc)
		final int choice = dialog.getSelected();
		Log.info("chose option: "+ choice);
		return choice ;
	}
	private void sleep(){
        try {
            Thread.sleep(GameInfo.USERINPUT_THREADSLEEP_TIME);
        } catch (final InterruptedException e) {
            Log.info("ooh nos, I have really no Idea what this means");
        }
	}
	private class DialogConstructor implements IUpdatable{

		private @Nullable Dialog dialog = null;
		private String text, title;
		public DialogConstructor(final String text, final String title){
			this.text = text;
			this.title = title;
		}
        @Override
        public void update(final int delta) {
            dialog = dialogProvider.get();
            getDialog().setText(text);
            getDialog().setTitle(title);
            msgBoxController.add(getDialog());
        }
		public Dialog getDialog() {
			return dialog;
		}
	}
}
