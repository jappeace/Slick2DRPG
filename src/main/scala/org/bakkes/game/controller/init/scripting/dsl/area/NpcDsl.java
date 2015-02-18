package org.bakkes.game.controller.init.scripting.dsl.area;

import com.google.inject.Inject;
import com.google.inject.Provider;
import groovy.lang.Closure;
import org.bakkes.game.controller.init.scripting.dsl.ADsl;
import org.bakkes.game.model.entity.npc.Person;
import org.newdawn.slick.util.Log;

import java.util.Collection;

public class NpcDsl extends ADsl{
	private Collection<Person> people;
	private @Inject Provider<PersonDsl> dslProvider;
	public void setPeople(final Collection<Person> people){
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
