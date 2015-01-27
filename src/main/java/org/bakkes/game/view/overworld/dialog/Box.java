package org.bakkes.game.view.overworld.dialog;

import org.bakkes.game.model.IShape;
import org.bakkes.game.view.IRenderable;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

/**
 * a box (to draw shit in like text, the shit has now a clear background).
 */
public class Box implements IRenderable, IShape{

	private Shape shape;

	private Color background = new Color(255,200,180);
	private Color border = new Color(0,0,255,150);
	private float borderWidth = 3;
	public Box(final float x, final float y, final float width, final float height){
		shape = new Rectangle(x,y,width,height);
	}
	@Override
	public void render(final Graphics g) {
		g.setColor(background);
		g.fill(shape);
		g.setLineWidth(5f);
		g.setColor(border);
		g.setLineWidth(borderWidth);
		g.drawRect(shape.getX(), shape.getY(), shape.getWidth()-borderWidth/2, shape.getHeight()-borderWidth/2);
		g.resetLineWidth();
	}
	public final Shape getShape() {
		return shape;
	}
	public final void setShape(final Shape shape) {
		this.shape = shape;
	}
	public final void setBackground(final Color background) {
		this.background = background;
	}
	public final void setBorder(final Color border) {
		this.border = border;
	}
	public final void setBorderWidth(final float borderWidth) {
		this.borderWidth = borderWidth;
	}
	@Override
	public float width() {
		return shape.getWidth();
	}
	@Override
	public float height() {
		return shape.getHeight();
	}
	@Override
	public float x() {
		return shape.getX();
	}
	@Override
	public float y() {
		return shape.getY();
	}
	@Override
	public void x(final float to) {
		shape.setX(to);

	}
	@Override
	public void y(final float to) {
		shape.setY(to);
	}

}
