package org.bakkes.game.view.components;

/**
 * a shape where text can be put in
 */
public interface ITextableShape extends IShape{
	/**
	 * @param the text to set
	 * @return a reference to the modified shape or itself
	 */
	ITextableShape setText(final String text);
	void setFont(final org.newdawn.slick.Font font);
}
