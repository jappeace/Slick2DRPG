package org.bakkes.game.entity;

import groovy.lang.Closure;

import org.bakkes.game.GameInfo;
import org.bakkes.game.ui.DialogBox;
import org.newdawn.slick.geom.Vector2f;


public class Person extends NPC{

	private Closure onInteract;
	private DialogBox dialog;
	public void setInteract(final Closure callback){
		onInteract = callback;
		onInteract.setDelegate(this);
	}
	public void setLocation(final Vector2f location){
		this.position = location;
	}

	public void interact(){
		onInteract.call();
	}

	public void dialog( final String text){
		dialog.setText(text);
		dialog.show();
	}

	public void give(final int ... items){
		for(final int item : items){
            GameInfo.getInstance().player.getInventory().addItem(item);
		}
	}
	public void setDialog(final DialogBox dialog) {
		this.dialog = dialog;
	}
}
