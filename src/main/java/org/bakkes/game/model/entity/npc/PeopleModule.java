package org.bakkes.game.model.entity.npc;

import java.util.LinkedList;
import java.util.List;

import org.bakkes.game.scripting.dsl.NpcDsl;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

public class PeopleModule extends AbstractModule{

	@Override
	protected void configure() {
	}

	private final List<Person> people = new LinkedList<>();
	@Singleton @Provides List<Person> providePeople(final NpcDsl npcdsl){
		return people;
	}
}
