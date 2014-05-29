package org.bakkes.game.math;

public class Vector2 {
	private float x;
	private float y;
	
	public Vector2(float x, float y) {
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
	
	public int getXI() {
		return (int) getX();
	}
	
	public int getYI() {
		return (int) getY();
	}
	
	public void add(Vector2 p) {
		this.x += p.x;
		this.y += p.y;
	}
	
	public Vector2 minusOperator(Vector2 p) {
		return new Vector2(this.x - p.x, this.y - p.y);
	}
	
	public void addX(float x) {
		this.x += x;
	}
	
	public void addY(float y) {
		this.y += y;
	}
	
	public Vector2 copy() {
		return new Vector2(x, y);
	}
	
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}
