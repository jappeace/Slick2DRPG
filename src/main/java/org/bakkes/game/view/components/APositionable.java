package org.bakkes.game.view.components;

import org.bakkes.game.view.IPositionable;
import org.newdawn.slick.geom.Vector2f;

public abstract class APositionable implements IPositionable{

    private final Vector2f position = new Vector2f();

	@Override
	public float x() {
		return position.x;
	}
	@Override
	public float y() {
		return position.y;
	}
	@Override
	public void x(final float to) {
		position.x = to;
        onChangePosition(position);
	}
	@Override
	public void y(final float to) {
		position.y = to;
        onChangePosition(position);
	}
    @Override
	public void setPosition(final Vector2f position) {
		this.position.x = position.x;
		this.position.y = position.y;
        onChangePosition(position);
	}
    @Override
	public Vector2f getPosition(){
    	return this.position;
    }

    /**
     * when the location changed this method is called,
     * use this to update you're composited view
     * @param position
     */
    protected void onChangePosition(final Vector2f position){}
}
