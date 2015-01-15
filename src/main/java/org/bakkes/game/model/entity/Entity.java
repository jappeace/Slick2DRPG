package org.bakkes.game.model.entity;

import org.bakkes.game.model.AModel;
import org.bakkes.game.model.map.Direction;
import org.bakkes.game.model.map.Tile;
import org.newdawn.slick.geom.Vector2f;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public abstract class Entity extends AModel{

	private @Named("position") @Inject Vector2f position;
	protected int facing = Direction.SOUTH;

	public Vector2f getPosition() {
		return position;
	}

	public void setPosition(final Vector2f to){
		position = to;
	}
	public Tile getTile() {
		return Tile.createFromPixelsCoordinates(position);
	}
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
}
