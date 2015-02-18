package org.bakkes.game.view.components;

import com.google.inject.Inject;
import org.apache.commons.lang3.StringUtils;
import org.newdawn.slick.Graphics;

/**
 * ##TextLine
 * this class tries to makes handling strings more managable (less painfull)
 */
public class TextLine extends AShape implements ITextableShape{
    private String text;

	/**
     * The fonts are fucking horrible
     * I have no Idea what the fuck I'm doing here
     */
    private @Inject org.newdawn.slick.Font font;

    public TextLine(){
    	text = "";
    }
    @Override
    public void render(final Graphics g) {
    	g.setFont(font);
        g.drawString(StringUtils.capitalize(text), x(), y());
        g.resetFont();
    }
	@Override
	public float width() {
		return font.getWidth(text);
	}
	@Override
	public float height() {
		return font.getHeight(text);
	}
	@Override
	public final ITextableShape setText(final String text) {
		this.text = text;
		return this;
	}
	public final void setFont(final org.newdawn.slick.Font font) {
		if(font == null){
			return;
		}
		this.font = font;
	}
}
