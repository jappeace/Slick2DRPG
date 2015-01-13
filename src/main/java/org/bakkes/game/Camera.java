package org.bakkes.game;
import org.bakkes.game.model.map.LayerdMap;
import org.bakkes.game.model.map.Tile;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

import com.google.inject.Inject;

//source: http://slick.ninjacave.com/forum/viewtopic.php?t=1906 (koopa)
public class Camera {


	@Inject private LayerdMap map;

   /** the GameContainer, used for getting the size of the GameCanvas */
   @Inject private GameContainer gc;

   /** the x-position of our "camera" in pixel */
   public float cameraX;

   /** the y-position of our "camera" in pixel */
   public float cameraY;

   /**
    * "locks" the camera on the given coordinates. The camera tries to keep the location in it's center.
    *
    * @param x the real x-coordinate (in pixel) which should be centered on the screen
    * @param y the real y-coordinate (in pixel) which should be centered on the screen
    */
   public void centerOn(final float x, final float y) {

       /** the height of the map in pixel */
       final int mapHeight =  map.getWidthInTiles() * Tile.WIDTH;

       /** the width of the map in pixel */
       final int mapWidth = map.getHeightInTiles() * Tile.HEIGHT;
      //try to set the given position as center of the camera by default
      cameraX = x - gc.getWidth()  / 2;
      cameraY = y - gc.getHeight() / 2;

      //if the camera is at the right or left edge lock it to prevent a black bar
      if(cameraX < 0) cameraX = 0;
      if(cameraX + gc.getWidth() > mapWidth) cameraX = mapWidth - gc.getWidth();

      //if the camera is at the top or bottom edge lock it to prevent a black bar
      if(cameraY < 0) cameraY = 0;
      if(cameraY + gc.getHeight() > mapHeight) cameraY = mapHeight - gc.getHeight();
   }

   /**
    * "locks" the camera on the center of the given Rectangle. The camera tries to keep the location in it's center.
    *
    * @param x the x-coordinate (in pixel) of the top-left corner of the rectangle
    * @param y the y-coordinate (in pixel) of the top-left corner of the rectangle
    * @param height the height (in pixel) of the rectangle
    * @param width the width (in pixel) of the rectangle
    */
   public void centerOn(final float x, final float y, final float height, final float width) {
      this.centerOn(x + width / 2, y + height / 2);
   }

   /**
    * "locks the camera on the center of the given Shape. The camera tries to keep the location in it's center.
    * @param shape the Shape which should be centered on the screen
    */
   public void centerOn(final Shape shape) {
      this.centerOn(shape.getCenterX(), shape.getCenterY());
   }

   public void centerOn(final Vector2f p) {
      this.centerOn(p.getX(), p.getY());
   }

   /**
    * draws the part of the map which is currently focussed by the camera on the screen
    */
   public void drawMap() {
      this.drawMap(0, 0);
   }

   /**
    * draws the part of the map which is currently focussed by the camera on the screen.<br>
    * You need to draw something over the offset, to prevent the edge of the map to be displayed below it<br>
    * Has to be called before Camera.translateGraphics() !
    * @param offsetX the x-coordinate (in pixel) where the camera should start drawing the map at
    * @param offsetY the y-coordinate (in pixel) where the camera should start drawing the map at
    */

   public void drawMap(final int offsetX, final int offsetY) {
       //calculate the offset to the next tile (needed by TiledMap.render())
       final int tileOffsetX = (int) - (cameraX % Tile.WIDTH);
       final int tileOffsetY = (int) - (cameraY % Tile.HEIGHT);

       //calculate the index of the leftmost tile that is being displayed
       final int tileIndexX = (int) (cameraX / Tile.WIDTH);
       final int tileIndexY = (int) (cameraY / Tile.HEIGHT);

       //finally draw the section of the map on the screen
       map.render(
             tileOffsetX + offsetX,
             tileOffsetY + offsetY,
             tileIndexX,
             tileIndexY,
                (gc.getWidth()  - tileOffsetX) / Tile.WIDTH  + 1,
                (gc.getHeight() - tileOffsetY) / Tile.HEIGHT + 1);
   }

   /**
    * Translates the Graphics-context to the coordinates of the map - now everything
    * can be drawn with it's NATURAL coordinates.
    */
   public void translateGraphics() {
      gc.getGraphics().translate(-cameraX, -cameraY);
   }
   /**
    * Reverses the Graphics-translation of Camera.translatesGraphics().
    * Call this before drawing HUD-elements or the like
    */
   public void untranslateGraphics() {
      gc.getGraphics().translate(cameraX, cameraY);
   }


}