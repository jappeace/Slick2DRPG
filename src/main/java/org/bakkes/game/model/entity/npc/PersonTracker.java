package org.bakkes.game.model.entity.npc;

import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class PersonTracker {
    private @Inject List<Person> people;
	public Person findPersonById(final int npc){
        if(npc != -1){
            for(final Person person : people){
                if(person.getId() == npc){
                    return person;
                }
            }
        }
        return null;
	}
}
