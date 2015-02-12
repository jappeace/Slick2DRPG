package org.bakkes.game.controller.init.scripting.dsl.area;

import groovy.lang.Closure;

import java.util.Arrays;
import java.util.Collection;

import nl.jappieklooster.annotation.Nullable;

import org.bakkes.game.controller.MessageBoxController;
import org.bakkes.game.controller.async.DelayedBatchExecutor;
import org.bakkes.game.controller.init.scripting.dsl.ADsl;
import org.bakkes.game.model.GameInfo;
import org.bakkes.game.model.IModel;
import org.bakkes.game.view.overworld.dialog.Dialog;
import org.bakkes.game.view.overworld.dialog.MessageBoxState;
import org.newdawn.slick.util.Log;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;
public class InteractDsl extends ADsl {
	private @Inject Provider<Dialog> dialogProvider;
	private @Inject Provider<PlayerDsl> playerDslProvider;
	private @Inject MessageBoxController msgBoxController;
	private @Inject @Named("in mainthread") DelayedBatchExecutor threadBridger;
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
		threadBridger.execute(new Runnable(){
            @Override
            public void run() {
                msgBoxController.add(title,text);
            }
        });
	}

	/**
	 * do stuff to the player
	 * @param commands
	 */
	public void selectPlayer(final Closure<Void> commands){
		// since we don't want to wait for user input, lets just syncrhonize this one
		threadBridger.execute(new Runnable(){
            @Override
            public void run() {
                delegate(commands, playerDslProvider.get());
            }
		});
	}

	/**
	 * show a tought
	 * @param text
	 */
	public void tought(final String text){
        dialog(text, "you think:");
	}

	public int decision(final String text){
		return decision(text, "decision:", Arrays.asList(new String[]{"yes", "no"}));
	}
	public int decision(final String text, final String title, final Collection<String> options){

		final DialogConstructor d = new DialogConstructor(text, title, options);

		threadBridger.execute(d);
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
			Log.info(e.getMessage());
		}
	}
	/**
	 * I hate threads
	 * Also java 7 for not having lambdas that could have replaced this bullshit
	 *
	 * this class' update function will be exuceted on the main thread
	 */
	private class DialogConstructor implements Runnable{

		private @Nullable Dialog dialog = null;
		private String text, title;
		private Collection<String> options;
		public DialogConstructor(final String text, final String title, final Collection<String> options){
			this.text = text;
			this.title = title;
			this.options = options;
		}
        @Override
        public void run() {
            final Dialog dialog = dialogProvider.get();
            dialog.setText(text);
            dialog.setTitle(title);
            dialog.add(options.toArray(new String[options.size()]));
            msgBoxController.add(dialog);
            this.dialog = dialog; // after this thread is done make it accesable
        }
        /**
         * the dialog will keep returining null until its filled by the update
         * @return
         */
		public Dialog getDialog() {
			return dialog;
		}
	}
}
