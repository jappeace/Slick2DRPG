
package org.bakkes.game;

import org.bakkes.game.map.Tile;
import org.junit.Assert;
import org.junit.Test;
import org.newdawn.slick.geom.Vector2f;

public class GridGraphicTest{

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
}
