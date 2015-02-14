package org.bakkes.game.model.entity;

import org.bakkes.game.model.map.Direction;
import org.bakkes.game.model.map.Tile;

public abstract class Character extends OverworldEntity {

	protected Direction facing = Direction.South;
	public Direction getFacing() {
		return facing;
	}
	public void setFacing(final Direction direction){
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
        final Direction facing = getFacing();
        final Tile result = new Tile();
        switch(facing){
        	case North:
        		result.top-=1;
        		break;
        	case South:
        		result.top+=1;
        		break;
        	case East:
        		result.left+=1;
        		break;
        	case West:
        		result.left-=1;
        		break;
        }
        return result;
	}
}
