package org.bakkes.game.scripting.dsl;

import groovy.lang.Closure;

import java.util.List;

import org.bakkes.game.model.entity.npc.Person;
import org.newdawn.slick.util.Log;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class NpcDsl extends ADsl{
	private List<Person> people;
	private @Inject Provider<Person> personProvider;
	private @Inject Provider<PersonDsl> dslProvider;
	public void setPeople(final List<Person> people){
		this.people = people;
	}
	public void person(final Closure commands){

		final Person person = personProvider.get();
		people.add(person);
		final PersonDsl factory = dslProvider.get();
		factory.setTarget(person);
		commands.setDelegate(factory);
		commands.call();

		if(!factory.isDone()){
			Log.warn("a person was not complete, remember to set both id and facing fields");
		}
	}
}
