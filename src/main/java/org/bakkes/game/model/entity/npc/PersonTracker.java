package org.bakkes.game.model.entity.npc;

import java.util.List;

import org.bakkes.game.model.map.Tile;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.sun.istack.internal.Nullable;

/**
 * npc's need to be reloaded for every area, this class garantees it happens, it also provides some commen methods
 * like finding an npc by tile
 */
@Singleton
public class PersonTracker {
    private @Inject Provider<List<Person>> people;
	public @Nullable Person findPersonByTile(final Tile tile) {
        for(final Person person : getPeople()){
        	if(person.getTile().equals(tile)){
        		return person;
        	}
        }
		return null;
	}

	public List<Person> getPeople(){
		return people.get();
	}
}
