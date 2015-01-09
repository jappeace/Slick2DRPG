
package org.bakkes.game;

import org.bakkes.game.map.GridGraphicTranslator;
import org.junit.Assert;
import org.junit.Test;
import org.newdawn.slick.geom.Vector2f;

public class GridGraphicTest{

	@Test
	public void testPixel(){
		final Vector2f pixelCoordinate = new Vector2f(0.4f,0.5f);
		final Vector2f expected = new Vector2f(0,0);
		Assert.assertEquals(expected, GridGraphicTranslator.PixelsToGrid(pixelCoordinate));
	}
	@Test
	public void testPixelEdge(){
		final Vector2f pixelCoordinate = new Vector2f(15.9f,15.99f);
		final Vector2f expected = new Vector2f(0,0);
		Assert.assertEquals(expected, GridGraphicTranslator.PixelsToGrid(pixelCoordinate));
	}
	@Test
	public void testPixelOtherEdge(){
		final Vector2f pixelCoordinate = new Vector2f(0.0f,0.0f);
		final Vector2f expected = new Vector2f(0,0);
		Assert.assertEquals(expected, GridGraphicTranslator.PixelsToGrid(pixelCoordinate));
	}
	@Test
	public void testPixelEdgeCase(){
		final Vector2f pixelCoordinate = new Vector2f(16f,16f);
		final Vector2f expected = new Vector2f(0,0);
		Assert.assertNotEquals(expected, GridGraphicTranslator.PixelsToGrid(pixelCoordinate));
	}
}
