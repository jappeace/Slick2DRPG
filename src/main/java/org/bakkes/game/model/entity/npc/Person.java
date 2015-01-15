package org.bakkes.game.model.entity.npc;

import groovy.lang.Closure;

import org.bakkes.game.model.entity.Entity;
import org.bakkes.game.model.entity.player.Player;
import org.bakkes.game.view.overworld.DialogBox;
import org.newdawn.slick.util.Log;

import com.google.inject.Inject;
import com.google.inject.Provider;


public class Person extends Entity{

	private Closure onInteract = null;
	@Inject private Provider<DialogBox> dialogProvider;
	@Inject private Player player;

	@Override
	public void setName(final String to){
		super.setName(to);
	}
	public void interact(){
		Log.info("talking to " + getName() + " on location: " + getPosition());
		if(onInteract == null){
			Log.info("no interaction present");
			return;
		}
		onInteract.call();
	}

	public void setInteract(final Closure callback){
		onInteract = callback;
		onInteract.setDelegate(this);
	}

	public void dialog( final String text){
		final DialogBox dialog = dialogProvider.get();
		dialog.setTitle(getName() + ":");
		dialog.setText(text);
		dialog.show();
	}

	public void give(final int ... items){
		for(final int item : items){
            player.getInventory().addItem(item);
		}
	}
}
