package org.bakkes.game.model.entity;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.bakkes.game.model.AModel;
import org.bakkes.game.model.map.Direction;
import org.bakkes.game.model.map.IBlocksTiles;
import org.bakkes.game.model.map.Tile;
import org.newdawn.slick.geom.Vector2f;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public abstract class Entity extends AModel implements IBlocksTiles{

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
	/**
	 * here we sort of assume all entities block 4 tiles
	 * the getTile(), and the one left below and leftbelow of it
	 */
	@Override
	public Collection<Tile> getBlockedTiles(){
		final List<Tile> result = new LinkedList<>();
		result.add(getTile());
		result.add(getTile().plus(new Tile(1,0)));
		result.add(getTile().plus(new Tile(0,1)));
		result.add(getTile().plus(new Tile(1,1)));
		return result;
	}

}
