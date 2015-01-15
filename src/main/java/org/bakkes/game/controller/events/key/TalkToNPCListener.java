package org.bakkes.game.controller.events.key;

import org.bakkes.game.model.entity.npc.Person;
import org.bakkes.game.model.entity.npc.PersonTracker;
import org.bakkes.game.model.entity.player.Player;
import org.bakkes.game.model.map.LayerdMap;
import org.lwjgl.input.Keyboard;

import com.google.inject.Inject;

public class TalkToNPCListener implements IKeyListener {
	private @Inject Player player;
	private @Inject PersonTracker peopleTracker;
	private @Inject LayerdMap map;

	@Override
	public void KeyDown(final int key, final char c) {
		if(key == Keyboard.KEY_SPACE) {
			final Person person = findFacingNPC();
			if(person == null){
				return;
			}
			person.interact();
		}
	}

	@Override
	public void KeyUp(final int key, final char c) {
		// TODO Auto-generated method stub

	}

	private Person findFacingNPC(){
		return peopleTracker.findPersonByTile(player.getDirectionTile().plus(player.getTile()));
	}

}
