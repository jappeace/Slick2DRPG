package org.bakkes.game.model;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.util.Log;

import com.google.inject.Singleton;

/**
 * creates images and puts them in a hashmap, this drastically improves loading times on pc's with a hdd
 * however it can hog a lot of memory (I have not solved this problem)
 *
 * the singleton annotation effectively makes it a singleton, the singleton exists as long as the same injector is used
 * this is technicly not a true singleton since you can make multiple of them in various ways
 */
@Singleton
public class ImageCache {
	/**
	 * Map<Path to img, Image instance>
	 * by using a path to image, the file system guarantees that we always talk about the same img
	 */
	private Map<String, Image> images = new HashMap<>();
	public Image load(final String path) throws SlickException{
		Image result = images.get(path);
		if(result == null){
			result = cache(path, new Image(path));
		}
		return result;
	}
	public SpriteSheet load(final String path, final int x, final int y) throws SlickException{
		SpriteSheet result = (SpriteSheet)images.get(path);
		if(result == null){
            result = (SpriteSheet)cache(path, new SpriteSheet(path,x,y));
		}
		return result;
	}
	private Image cache(final String path, final Image img){
		Log.info("caching image into ram: " + path);
        images.put(path, img);
        return img;
	}
}
