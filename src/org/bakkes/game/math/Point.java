package org.bakkes.game.math;

public class Point {
	private float x;
	private float y;
	
	public Point(float x, float y) {
		super();
		this.x = x;
		this.y = y;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	public void add(Point p) {
		this.x += p.x;
		this.y += p.y;
	}
	
	public Point minusOperator(Point p) {
		return new Point(this.x - p.x, this.y - p.y);
	}
	
	public void addX(float x) {
		this.x += x;
	}
	
	public void addY(float y) {
		this.y += y;
	}
}
