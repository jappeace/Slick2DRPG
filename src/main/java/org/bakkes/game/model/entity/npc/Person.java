package org.bakkes.game.model.entity.npc;

import groovy.lang.Closure;

import org.bakkes.game.model.entity.Character;
import org.newdawn.slick.util.Log;


public class Person extends Character{

	private Closure onInteract = null;

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
	}
}
