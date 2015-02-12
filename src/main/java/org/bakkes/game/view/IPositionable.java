package org.bakkes.game.view;

import org.newdawn.slick.geom.Vector2f;

/**
 * easy function calls to (probably) a vector backend
 */
public interface IPositionable {
	/**
	 * implement this to create a propper positionable
	 * @return
	 */
    public Vector2f getPosition();

	public default float x() {
		return getPosition().x;
	}

	public default float y() {
		return getPosition().y;
	}

	public default void x(final float to) {
		getPosition().x = to;
		onChangePosition(getPosition());
	}

	public default void y(final float to) {
		getPosition().y = to;
		onChangePosition(getPosition());
	}

	public default void setPosition(final Vector2f position) {
		x(position.x);
		y(position.y);
	}

    /**
     * when the location changed this method is called,
     * use this to update your composite view
     * @param position
     */
	public default  void onChangePosition(final Vector2f newLocation){}
}
