package org.bakkes.game.view.components;

import java.util.LinkedList;
import java.util.List;

import org.bakkes.game.view.IRenderable;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

/**
 * don't extends a view otherwise stackoverflow
 *
 * a simple utility to emulate the cout << "" kind of stuff on screen
 */
public class LineWriterView implements IRenderable{

	private Vector2f location = new Vector2f();
	private Vector2f start = new Vector2f();
	private List<TextLine> lines = new LinkedList<>();
	public Color color = Color.black;

	@Override
	public void render(final Graphics g) {
		g.setColor(color);
		for(final TextLine l : lines){
			l.render(g);
		}
	}

	public void setLocation(final Vector2f location) {
		this.start = location.copy(); // to calculate the current hight
		this.location = location.copy(); // mutable location
	}
	public void setLocation(final float x, final float y) {
		setLocation(new Vector2f(x,y));
	}
	public float getHeight(){
		return location.y - start.y;
	}

	public void write(final TextLine line){
		lines.add(line);
		getLocation().y += line.height();
	}
	public void write(final String str){
		write(new TextLine(getLocation(), str));
	}
	public void clear(){
		lines.clear();
	}
	public Vector2f getLocation() {
		return location;
	}
	public int lineCount(){
		return lines.size();
	}
}
