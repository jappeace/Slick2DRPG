package org.bakkes.game.controller.scripting.dsl.area;

import groovy.lang.Closure;

import java.util.List;

import org.bakkes.game.controller.scripting.dsl.ADsl;
import org.bakkes.game.model.entity.npc.Person;
import org.newdawn.slick.util.Log;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class NpcDsl extends ADsl{
	private List<Person> people;
	private @Inject Provider<PersonDsl> dslProvider;
	public void setPeople(final List<Person> people){
		this.people = people;
	}
	public void person(final Closure<Void> commands){

		final PersonDsl factory = dslProvider.get();
		delegate(commands, factory);
		final Person person = factory.getTarget();
		people.add(person);

		if(!factory.isDone()){
			Log.warn("a person was not complete, remember to set both position and facing fields");
		}
	}
}
