package org.bakkes.game.model.map;

import nl.jappieklooster.groovy.math.IMinusOperator;
import nl.jappieklooster.groovy.math.IMultiply;
import nl.jappieklooster.groovy.math.IPlusOperator;

import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.util.pathfinding.Path.Step;


public class Tile implements IPlusOperator<Tile>, IMinusOperator<Tile>, IMultiply<Vector2f>{
	public static final int WIDTH = 16;
	public static final int HEIGHT = 16;

	public int left;
	public int top;

	public Tile(){
		this(0,0);
	}
	public Tile(final Tile source){
		this(source.left, source.top);
	}
	public Tile(final Step source){
		this(source.getX(),source.getY());
	}
	public Tile(final Vector2f coordinates){
		this((int)coordinates.x, (int)coordinates.y);
	}
	public Tile(final int left, final int top){
		this.left = left;
		this.top = top;
	}
	public static Tile createFromPixelsCoordinates(final Vector2f pixelCoordinates){
		final Tile result = new Tile();
		// half pixels don't exist in my world
		result.left = (int)(pixelCoordinates.x - (pixelCoordinates.x % Tile.WIDTH)) / Tile.WIDTH;
		result.top = (int)(pixelCoordinates.getY() - (pixelCoordinates.getY() % Tile.HEIGHT)) / Tile.HEIGHT;
		return result;
	}

	public Vector2f toVector(){
		return new Vector2f(top,left);
	}
	public Vector2f topLeftPixels(){
		return new Vector2f(left*WIDTH, top*HEIGHT);
	}
	public Vector2f bottomRightPixels(){
		return new Vector2f((left+1)*WIDTH, (top+1)*HEIGHT);
	}

	public boolean contains(final Vector2f pixels) {
		final Vector2f tl = topLeftPixels();
		if(tl.x > pixels.x){
			return false;
		}
		if(tl.y > pixels.y){
			return false;
		}
		final Vector2f br = bottomRightPixels();
		if(br.x <= pixels.x){
			return false;
		}
		if(br.y <= pixels.y){
			return false;
		}
		return true;
	}

	/**
	 *
	 * @param p
	 * @return
	 */
	public static Vector2f PixelsToGridPixels(final Vector2f p) {
		return new Vector2f(
            (p.getX() - (p.getX() % Tile.WIDTH)),
            (p.getY() - (p.getY() % Tile.HEIGHT))
        );
	}
	@Override
	public Tile minus(final Tile tile){
		return new Tile(this.left - tile.left, this.top - tile.top);
	}
	@Override
	public Tile plus(final Tile tile){
		return new Tile(this.left + tile.left, this.top + tile.top);
	}
	@Override
	public String toString(){
		return "Tile(" + left + "," + top + ")";
	}
	@Override
	public Vector2f multiply(final Vector2f righthandSide) {
		return new Vector2f(left * righthandSide.x, top * righthandSide.y);
	}
}
