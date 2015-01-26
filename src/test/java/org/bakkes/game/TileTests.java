
package org.bakkes.game;

import org.bakkes.game.model.map.Direction;
import org.bakkes.game.model.map.Tile;
import org.junit.Assert;
import org.junit.Test;
import org.newdawn.slick.geom.Vector2f;

public class TileTests{

	private void testContains(final int left, final int top, final float x, final float y){
		final Vector2f pixelCoordinate = new Vector2f(x,y);
		final Tile tile = new Tile(left,top);
		Assert.assertTrue(tile.contains(pixelCoordinate));
	}
	private void testNotContains(final int left, final int top, final float x, final float y){
		final Vector2f pixelCoordinate = new Vector2f(x,y);
		final Tile tile = new Tile(left,top);
		Assert.assertFalse(tile.contains(pixelCoordinate));

	}
	@Test
	public void testPixel(){
		testContains(0,0,0.4f,0.5f);
	}
	@Test
	public void testPixelEdge(){
		testContains(0,0,15.9f,15.99f);
		testContains(1,0,31.9f,15.99f);
	}
	@Test
	public void testPixelOtherEdge(){
		testContains(0,0,0f,0f);
		testContains(1,1,16f,16f);
	}
	@Test
	public void testPixelOverEdge(){
        testNotContains(0,0,16f,16f);
        testNotContains(1,1,15.9f,15.9f);
	}
	@Test
	public void hashTest(){
		Assert.assertTrue(new Tile(1,0).hashCode() != new Tile(0,1).hashCode());
		Assert.assertTrue(new Tile(1,0).hashCode() == new Tile(1,0).hashCode());
	}

	private void directionTest(final Direction excpected, final Tile input){
		Assert.assertEquals(excpected, input.getDirection());
	}
	@Test
	public void directionTestsNorth(){
		final Direction expected = Direction.North;
		Tile input = new Tile(0,-1);
		directionTest(expected, input);
		input = new Tile(1,-3);
		directionTest(expected, input);
		input = new Tile(-1,-3);
		directionTest(expected, input);
	}
	@Test
	public void directionTestsEast(){
		final Direction expected = Direction.East;
		Tile input = new Tile(1,0);
		directionTest(expected, input);
		input = new Tile(3,1);
		directionTest(expected, input);
		input = new Tile(3,-1);
		directionTest(expected, input);
	}
	@Test
	public void directionTestsWest(){
		final Direction expected = Direction.West;
		Tile input = new Tile(-1,0);
		directionTest(expected, input);
		input = new Tile(-3,1);
		directionTest(expected, input);
		input = new Tile(-3,-1);
		directionTest(expected, input);
	}
	@Test
	public void directionTestsSouth(){
		final Direction expected = Direction.South;
		Tile input = new Tile(0,1);
		directionTest(expected, input);
		input = new Tile(1,3);
		directionTest(expected, input);
		input = new Tile(-1,3);
		directionTest(expected, input);
	}
}
