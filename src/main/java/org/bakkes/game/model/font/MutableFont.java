package org.bakkes.game.model.font;


import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.TrueTypeFont;

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
public class MutableFont implements Font{

    private static class Default{
        public static final String name = java.awt.Font.SANS_SERIF;
        public static final int decoration = java.awt.Font.PLAIN;
        public static final int size = 15;
        public static final boolean antiAliasing = true;
        public static final Font font = new TrueTypeFont(new java.awt.Font(name, decoration, size), antiAliasing);
    }
	private String name = Default.name;
	private int decoration = Default.decoration;
	private int size = Default.size;
	private boolean antiAliasing = Default.antiAliasing;

    private Font font = Default.font;

    private void update(){
        font = new TrueTypeFont(new java.awt.Font(name, decoration, size), antiAliasing);
    }


	@Override
	public int getWidth(final String str) {
		return font.getWidth(str);
	}



	public final String getName() {
		return name;
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

	public final void setName(final String name) {
		this.name = name;
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
