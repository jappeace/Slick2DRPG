package org.bakkes.game.model.font;


import java.util.HashMap;
import java.util.Map;

import org.bakkes.game.model.AModel;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.Log;

/**
 * ##Font
 * The standart java fonts are imutable, which is great if you are into that kind of crap
 * However I need something more flexible (and easy to use), I like to create things first (with reasonable defautls)
 * and then configure later
 *
 * this class will contain all possible font data with reasonable defaults
 * I will construct more complex fonts based on this one
 *
 * I want to be able to create this default font and then modify it
 *
 * aperantly is font creating quite heavy, so the default font is now stored staticly (usualy it is not changed)
 * I still experiance frame drops when loading other fonts, so creating some kind of caching might be good
 * but I don't think I'll do it, I'm to lazy anyways to use many fonts
 */
public class MutableFont extends AModel implements Font{

	private int decoration = java.awt.Font.PLAIN;
	private int size = 15;
	private boolean antiAliasing = true;

    private Font font;
    private static final Map<String, Font> cache = new HashMap<>();


    MutableFont(){
    	setName(java.awt.Font.SANS_SERIF);
    	update();
    }

    private synchronized Font loadFont(){
    	Font result = cache.get(toString());
    	if(result == null){
    		result = new TrueTypeFont(new java.awt.Font(getName(), decoration, size), antiAliasing);
    		Log.info("loading new font " + toString());
    		cache.put(toString(), result);
    	}
    	return result;
    }
    private void update(){
        font = loadFont();
    }

    @Override
	public String toString(){
    	return super.toString() + "[" + "decoration: " + decoration + " size: " + size + " antiAliasing: " + antiAliasing +" ]";
    }

	@Override
	public int getWidth(final String str) {
		return font.getWidth(str);
	}



	public final int getDecoration() {
		return decoration;
	}

	public final int getSize() {
		return size;
	}

	public final boolean isAntiAliasing() {
		return antiAliasing;
	}

	@Override
	public final void setName(final String name) {
		super.setName(name);
		update();
	}

	public final void setDecoration(final int decoration) {
		this.decoration = decoration;
		update();
	}

	public final void setSize(final int size) {
		this.size = size;
		update();
	}

	public final void setAntiAliasing(final boolean antiAliasing) {
		this.antiAliasing = antiAliasing;
		update();
	}

	@Override
	public int getHeight(final String str) {
		return font.getHeight(str);
	}



	@Override
	public int getLineHeight() {
		return font.getLineHeight();
	}



	@Override
	public void drawString(final float x, final float y, final String text) {
		font.drawString(x, y, text);
	}



	@Override
	public void drawString(final float x, final float y, final String text, final Color col) {
		font.drawString(x, y, text, col);
	}



	@Override
	public void drawString(final float x, final float y, final String text, final Color col,
			final int startIndex, final int endIndex) {
		font.drawString(x, y, text, col, startIndex, endIndex);
	}
}
