package org.bakkes.game.view.components;

import java.util.LinkedList;
import java.util.List;

import org.bakkes.game.view.IRenderable;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;

import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * don't extends a view otherwise stackoverflow
 *
 * a simple utility to emulate the cout << "" kind of stuff on screen
 */
public class LineWriter extends APositionable implements IRenderable{

	private List<TextLine> lines = new LinkedList<>();
	private @Inject Provider<TextLine> textLineProvider;
	public Color color = Color.black;

	@Override
	public void render(final Graphics g) {
		g.setColor(color);
		for(final TextLine l : lines){
			l.render(g);
		}
	}

	public float getHeight(){
		float lowY = Float.POSITIVE_INFINITY;
		float highY = Float.NEGATIVE_INFINITY;
		for(final TextLine t : lines){
			if(lowY > t.y()){
				lowY = t.y();
			}
			if(highY < t.y()){
				highY = t.y();
			}
		}
		if(lowY == Float.POSITIVE_INFINITY || highY == Float.NEGATIVE_INFINITY){
			return 0;
		}
		return highY - lowY;
	}

	public void write(final String str){
		write(str, null);
	}
	public void write(final String str, final Font font){
		final TextLine t = textLineProvider.get();
		t.setText(str);
		t.y(y());
		t.x(x());
		t.setFont(font);
		write(t);
	}
	public void write(final TextLine line){
		lines.add(line);
		y(y()+line.height());
	}
	public void clear(){
		lines.clear();
	}
	public int lineCount(){
		return lines.size();
	}
}
