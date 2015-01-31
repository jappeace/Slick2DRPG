package org.bakkes.game.model;

/**
 * some models have sprites which have weird names (a number for example)
 * if so implement this interface
 */
public interface IHasSpriteName extends IModel{
	public String getSpriteName();
	public void setSpriteName(String to);
}
