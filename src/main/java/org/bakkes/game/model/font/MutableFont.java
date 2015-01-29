package org.bakkes.game.model.font;

import java.awt.Font;

import org.newdawn.slick.Color;
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
 */
public class MutableFont implements org.newdawn.slick.Font{

	private String name = Font.SANS_SERIF;
	private int decoration = Font.PLAIN;
	private int size = 12;
	private boolean antiAliasing = true;

    private org.newdawn.slick.Font font;

    public MutableFont(){
    	update();
    }

    private void update(){
        font = new TrueTypeFont(new Font(name, decoration, size), antiAliasing);
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
