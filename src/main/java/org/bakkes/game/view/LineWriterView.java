package org.bakkes.game.view;

import java.util.LinkedList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

public class LineWriterView extends AView{

	private Vector2f location = new Vector2f();
	private List<Line> lines = new LinkedList<>();
	public float lineIncrease = 15f;

	@Override
	public void render(final GameContainer gc, final Graphics g) {
		for(final Line l : lines){
			l.render(gc, g);
		}
	}


	public void setLocation(final Vector2f location) {
		this.location = location;
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
		public void render(final GameContainer gc, final Graphics g) {
			g.drawString(string, position.x, position.y);
		}
	}
}
