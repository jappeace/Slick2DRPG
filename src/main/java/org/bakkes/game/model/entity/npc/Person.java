package org.bakkes.game.model.entity.npc;

import groovy.lang.Closure;

import org.bakkes.game.model.entity.Entity;
import org.bakkes.game.model.entity.player.Player;
import org.bakkes.game.view.overworld.DialogBox;
import org.newdawn.slick.util.Log;

import com.google.inject.Inject;


public class Person extends Entity{

	private Closure onInteract = null;
	@Inject private DialogBox dialog;
	@Inject private Player player;

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
		dialog.setText(text);
		dialog.show();
	}

	public void give(final int ... items){
		for(final int item : items){
            player.getInventory().addItem(item);
		}
	}
}
