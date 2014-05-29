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
	
	public Vector2 divideBy(float f) {
		return new Vector2(this.x / f, this.y / f);
	}
	
	public Vector2 multiply(float f) {
		return new Vector2(this.x * f, this.y * f);
	}
	
	public Vector2 minusOperator(Vector2 p) {
		return new Vector2(this.x - p.x, this.y - p.y);
	}
	
	public void normalize() {
		float length = this.length();
		this.x /= length;
		this.y /= length;
	}
	
	public void truncate(float max) {
		if(this.length() > max) {
			this.normalize();
			this.x *= max;
			this.y *= max;
		}
	}
	
	public Vector2 normalizeCopy() {
		Vector2 cp = copy();
		cp.normalize();
		return cp;
	}
	
	public float length() {
		return (float) Math.sqrt(x * x + y * y);
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
	
	public Vector2 perp() {
		return new Vector2(-y, x);
	}
	
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}
