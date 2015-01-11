package org.bakkes.game.model.entity;

import org.bakkes.game.model.map.Direction;
import org.bakkes.game.model.map.Tile;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

public abstract class Entity {
	private Vector2f position; // not encapsulated anyways
	protected int facing = Direction.SOUTH;

	public void init(final GameContainer gc) {}
	public abstract void update(GameContainer gc, int delta);
	public abstract void render(GameContainer gc, Graphics g);

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
	/**
	 * used a lot, anoying to do
	 * @return
	 */
	public Tile getFacingTile(){
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
