package org.bakkes.game.view.components;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

import org.bakkes.game.view.IRenderable;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * creates a nice select input
 * basicly the player sees a couple of texts he can chose from and select
 */
public class Menu extends AShape implements IRenderable{

	private Collection<TextLine> options = new LinkedList<>();
	private @Inject Provider<TextLine> textLineProvider;

	private int selected = 0;
	private float padding = 15;
	private float overrideHeight = -1;
	private float overrideWidth = -1;
	private final Box backgroundBox = new Box();
	private final Box selectBox = new Box();
	public Menu(){
		selectBox.setBackground(new Color(0,0,255,50));
		selectBox.setBorder(new Color(0,0,255,80));
		selectBox.setBorderWidth(2f);
	}

	private void updateShape(){
		backgroundBox.x(x());
		backgroundBox.y(y());
		backgroundBox.width(width() + padding * 2);
		if(isOverrideHeight()){
            backgroundBox.height(height() + padding);
		}else{
            backgroundBox.height(height()*options.size() + padding);
		}
		selectBox.x(x() + padding /2);
		selectBox.y(y() + padding);
		selectBox.width(width() + padding);
	}
	public void add(final String ... options){
		for(final String option : options){
			final TextLine t = textLineProvider.get();
			t.setText(option);
			add(t);
		}
	}
	public void add(final TextLine ... options){
		this.options.addAll(Arrays.asList(options));
		updateShape();
	}
	@Override
	public void render(final Graphics g) {
		backgroundBox.render(g);

        int linenr = 0;
        for(final TextLine option : options){
        	option.x(backgroundBox.x() + backgroundBox.width()/2 - option.width() /2);
        	option.y(backgroundBox.y() + padding/2 + option.height()*linenr);
        	if(linenr == selected){
                selectBox.height(option.height());
                selectBox.y(backgroundBox.y() + padding/2 + selected*selectBox.height());
                selectBox.render(g);
        	}
        	g.setColor(Color.black);
        	option.render(g);
        	linenr++;
        }
	}

	public void up(){
		selected = (selected + 1) % options.size();
	}
	public void down(){
		selected--;
		if(selected < 0){
			selected = options.size()-1;
		}
	}

	@Override
	public void x(final float to) {
		super.x(to);
		updateShape();
	}
	@Override
	public void y(final float to) {
		super.y(to);
		updateShape();
	}
    @Override
	public void setPosition(final Vector2f position) {
		super.setPosition(position);
		updateShape();
	}

	public void width(final float to) {
		overrideWidth = to;
		updateShape();
	}
	public void height(final float to) {
		overrideHeight = to;
		updateShape();
	}
	@Override
	public float width() {
		if(overrideWidth > 0){
			return overrideWidth;
		}
		float result = 0;
        for(final TextLine option : options){
        	if(option.width() > result){
        		result = option.width();
        	}
        }
		return result;
	}

	private boolean isOverrideHeight(){
		return overrideHeight > 0;
	}
	@Override
	public float height() {
		if(isOverrideHeight()){
			return overrideHeight;
		}
		float result = 0;
        for(final TextLine option : options){
        	if(option.height() > result){
        		result = option.height();
        	}
        }
		return result;
	}

	public int getSelected(){
		return selected;
	}
}

