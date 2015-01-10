package org.bakkes.game.scripting.dsl;

import groovy.lang.Closure;

import java.util.List;

import org.bakkes.game.entity.Person;

public class NpcDsl extends ADsl{
	private List<Person> people;
	public NpcDsl(final List<Person> people) {
		this.people = people;
	}
	public void person(final Closure commands){

		final Person person = new Person();
		people.add(person);
		final PersonDsl factory = new PersonDsl();
		factory.setTarget(person);
		commands.setDelegate(factory);
		commands.call();
	}
}
