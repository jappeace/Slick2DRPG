package org.bakkes.game.model;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import com.google.inject.Singleton;

@Singleton
public class ImageCache {
	private Map<String, Image> images = new HashMap<>();
	public Image load(final String path) throws SlickException{
		Image result = images.get(path);
		if(result == null){
			result = new Image(path);
			images.put(path, result);
		}
		return result;
	}
	public SpriteSheet load(final String path, final int x, final int y) throws SlickException{
		SpriteSheet result = (SpriteSheet)images.get(path);
		if(result == null){
            result = new SpriteSheet(path,x,y);
            images.put(path, result);
		}
		return result;
	}
}
