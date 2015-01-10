package org.bakkes.game.entity;

import groovy.lang.Closure;


public class Person extends NPC{

	private Closure onInteract;
	public void setInteract(final Closure callback){
		onInteract.setDelegate(this);
		onInteract = callback;
	}

	public void interact(){
		onInteract.call();
	}

	public void dialog( final String dialog){
	}

	public void give(final int itemId){
	}
}
