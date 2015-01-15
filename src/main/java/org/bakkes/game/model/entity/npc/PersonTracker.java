package org.bakkes.game.model.entity.npc;

import java.util.List;

import org.bakkes.game.model.map.Tile;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.sun.istack.internal.Nullable;

@Singleton
public class PersonTracker {
    private @Inject Provider<List<Person>> people;
	public @Nullable Person findPersonByTile(final Tile tile) {
        for(final Person person : people.get()){
        	if(person.getTile().equals(tile)){
        		return person;
        	}
        }
		return null;
	}
}
