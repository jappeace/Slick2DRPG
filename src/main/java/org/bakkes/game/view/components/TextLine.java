package org.bakkes.game.view.components;

import static com.sun.xml.internal.ws.util.StringUtils.capitalize;

import java.awt.Font;

import org.bakkes.game.view.IRenderable;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Vector2f;

/**
 * ##TextLine
 * this class tries to makes handling strings more managable (less painfull)
 */
public class TextLine extends AShape implements IRenderable{
    private String text;

	/**
     * The fonts are fucking horrible
     * I have no Idea what the fuck I'm doing here
     */
    private org.newdawn.slick.Font font;

    public TextLine(){
    	this(new Vector2f(),"");
    }
    public TextLine(final String str){
    	this(new Vector2f(),str);
    }
    public TextLine(final Vector2f location, final String str){
    	font = new TrueTypeFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12), true);
        setPosition(location);
        text = str;
    }
    @Override
    public void render(final Graphics g) {
    	g.setFont(font);
        g.drawString(capitalize(text), x(), y());
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
	public final void setText(final String text) {
		this.text = text;
	}
	public final void setFont(final org.newdawn.slick.Font font) {
		this.font = font;
	}
}
