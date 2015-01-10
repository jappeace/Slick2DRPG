package org.bakkes.game.scripting.dsl;

import groovy.lang.Closure;

import org.bakkes.game.entity.Person;

public class PersonDsl extends ADsl{

	private Person target;

	public void setTarget(final Person target) {
		this.target = target;
	}

	public void id(final int id){
		target.setId(id);
	}

	public void onInteract(final Closure callback){
		target.setInteract(callback);
	}


}
