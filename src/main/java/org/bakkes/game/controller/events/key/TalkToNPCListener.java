package org.bakkes.game.controller.events.key;

import org.bakkes.game.model.entity.Player;
import org.bakkes.game.model.entity.npc.Person;
import org.bakkes.game.model.entity.npc.PersonTracker;
import org.bakkes.game.model.map.Direction;
import org.bakkes.game.model.map.LayerdMap;
import org.bakkes.game.model.map.Tile;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.util.Log;

import com.google.inject.Inject;

public class TalkToNPCListener implements IKeyListener {
	private @Inject Player player;
	private @Inject PersonTracker peopleTracker;
	private @Inject LayerdMap map;

	@Override
	public void KeyDown(final int key, final char c) {
		if(key == Keyboard.KEY_SPACE) {
			final int facingNpc = findFacingNPC();
			Log.info("Facing: " + facingNpc);
			final Person person = peopleTracker.findPersonById(facingNpc);
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

	private int findFacingNPC(){
		Tile facingTile = player.getTile();
		switch(player.getFacing()) {
		case Direction.NORTH:
			facingTile = facingTile.plus(new Tile(0,-1));
			break;
		case Direction.EAST:
			facingTile = facingTile.plus(new Tile(2,0));
			break;
		case Direction.SOUTH:
			facingTile = facingTile.plus(new Tile(0,2));
			break;
		case Direction.WEST:
			facingTile = facingTile.plus(new Tile(-2,1));
			break;
		}
		return map.getNPCidOn(facingTile);
	}

}
