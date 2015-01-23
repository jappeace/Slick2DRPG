package org.bakkes.game.model.entity;

import java.util.Collection;

import org.bakkes.game.model.map.Direction;
import org.bakkes.game.model.map.Tile;

public abstract class Character extends OverworldEntity {

	protected int facing = Direction.SOUTH;
	public int getFacing() {
		return facing;
	}
	public void setFacing(final int direction){
		facing = direction;
	}

	private boolean isWalking = false;
	public void onEnterNewTile(){
		isWalking = true;
	}
	public void onFinishedWalking(){
		isWalking = false;
	}
	public boolean isWalking(){
		return isWalking;
	}
	/**
	 * used a lot, anoying to do
	 * @return
	 */
	public Tile getDirectionTile(){
        final int facing = getFacing();
        final Tile result = new Tile();
        switch(facing){
        	case Direction.NORTH:
        		result.top-=1;
        		break;
        	case Direction.SOUTH:
        		result.top+=1;
        		break;
        	case Direction.EAST:
        		result.left+=1;
        		break;
        	case Direction.WEST:
        		result.left-=1;
        		break;
        }
        return result;
	}
	/**
	 * here we sort of assume all entities block 4 tiles
	 * the getTile(), and the one left below and leftbelow of it
	 */
	@Override
	public Collection<Tile> getBlockedTiles(){
		final Collection<Tile> result = super.getBlockedTiles();
		result.add(getTile().plus(new Tile(1,0)));
		result.add(getTile().plus(new Tile(0,1)));
		result.add(getTile().plus(new Tile(1,1)));
		return result;
	}

}
