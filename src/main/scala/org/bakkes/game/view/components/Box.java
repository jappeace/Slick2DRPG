package org.bakkes.game.view.components;

import org.bakkes.game.view.IRenderable;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

/**
 * a box (to draw shit in like text, the shit has now a clear background).
 */
public class Box implements IRenderable, IShape{

	private Shape shape;

	private Color background = new Color(255,200,180);
	private Color border = new Color(0,0,255,150);
	private float borderWidth = 3;
	public Box(){
		// make it big so configuration won't be forgotten
		this(0,0,400,500);
	}
	/**
	 * this is one of the few objects that can safly be constructed with new
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Box(final float x, final float y, final float width, final float height){
		shape = createShape(x,y,width,height);
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
	/**
	 * As everyone knows boxes are resizable in contradiction to shapes
	 * you can step on one for example
	 */
	public void width(final float to){
		shape = createShape(x(), y(), to, height());
	}
	public void height(final float to){
		shape = createShape(x(), y(), width(), to);
	}
	@Override
	public void x(final float to) {
		shape.setX(to);

	}
	@Override
	public void y(final float to) {
		shape.setY(to);
	}
	private Shape createShape(final float x, final float y, final float width, final float height){
		return new Rectangle(x,y,width,height);
	}
	@Override
	public void setPosition(final Vector2f position) {
		shape.setLocation(position);
	}
	@Override
	public Vector2f getPosition() {
		return shape.getLocation();
	}
	public float getBorderWidth(){
		return borderWidth;
	}
	public Color getBorderColor(){
		return border;
	}

}
