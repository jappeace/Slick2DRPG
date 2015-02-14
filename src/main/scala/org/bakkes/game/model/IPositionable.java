package org.bakkes.game.model;

import org.newdawn.slick.geom.Vector2f;

/**
 * easy function calls to (probably) a vector backend
 */
public interface IPositionable {
	/**
	 * implement this to create a proper positionable
	 * @return
	 */
    Vector2f getPosition();

	float x();

	float y();

	void x(final float to);

	void y(final float to) ;

	void setPosition(final Vector2f position);

    /**
     * when the location changed this method is called,
     * use this to update your composite view
     * @param position
     */
	 void onChangePosition(final Vector2f newLocation);
}
