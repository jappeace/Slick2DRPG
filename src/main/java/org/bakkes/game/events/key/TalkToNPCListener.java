package org.bakkes.game.events.key;

import org.bakkes.game.World;
import org.bakkes.game.entity.Direction;
import org.bakkes.game.entity.Player;
import org.bakkes.game.map.LayerdMap;
import org.bakkes.game.map.Tile;
import org.bakkes.game.state.OverworldState;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.util.Log;

public class TalkToNPCListener implements IKeyListener {
	private OverworldState game;


	public TalkToNPCListener(final OverworldState playingGameState) {
		this.game = playingGameState;
	}

	@Override
	public void KeyDown(final int key, final char c) {
		if(key == Keyboard.KEY_SPACE) {
			final int facingNpc = findFacingNPC();
			Log.info("Facing: " + facingNpc);
			if(facingNpc != -1)
				Log.warn("Load the interaction here");
		}
	}

	@Override
	public void KeyUp(final int key, final char c) {
		// TODO Auto-generated method stub

	}

	private int findFacingNPC(){
		final Player player = game.getPlayer();
		Tile facingTile = player.getTile();
		switch(player.getDirection()) {
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
		final LayerdMap map = World.getWorld().getLayerMap();
		return map.getNPCidOn(facingTile);
	}

}
