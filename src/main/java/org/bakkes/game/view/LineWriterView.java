package org.bakkes.game.view;

import java.util.LinkedList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

import com.sun.xml.internal.ws.util.StringUtils;

/**
 * don't extends a view otherwise stackoverflow
 *
 * a simple utility to emulate the cout << "" kind of stuff on screen
 */
public class LineWriterView implements IRenderable{

	private Vector2f location = new Vector2f();
	private Vector2f start = new Vector2f();
	private List<Line> lines = new LinkedList<>();
	public float lineIncrease = 15f;
	public Color color = Color.black;

	@Override
	public void render(final Graphics g) {
		g.setColor(color);
		for(final Line l : lines){
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

	public void write(final String str){
		lines.add(new Line(getLocation(), str));
		getLocation().y += lineIncrease;
	}

	public void clear(){
		lines.clear();
	}
	public Vector2f getLocation() {
		return location;
	}
	private static class Line implements IRenderable{
		public Vector2f position;
		String string;
		public Line(final Vector2f location, final String str){
			position = new Vector2f(location);
			string = str;
		}
		@Override
		public void render(final Graphics g) {
			g.drawString(StringUtils.capitalize(string), position.x, position.y);
		}
	}
}
