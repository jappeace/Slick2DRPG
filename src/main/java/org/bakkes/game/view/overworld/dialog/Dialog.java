package org.bakkes.game.view.overworld.dialog;

import org.bakkes.game.controller.input.IKeyListener;
import org.bakkes.game.controller.input.Key;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Dialog extends MessageBox implements IKeyListener{

	private String[] options = new String[]{"yes", "no"};

	private int selected = 0;
	private final Box anwserBox = new Box(
			box.x() + box.width()*0.8f - margin,
			box.y() - margin,
            box.width()*0.15f,
            box.height()*0.3f
			);
	private final Box selectBox = new Box(
			anwserBox.x() + margin /2,
			anwserBox.y() + margin,
            anwserBox.width() - margin,
            18
        );
	public Dialog(){
		selectBox.setBackground(new Color(0,0,255,50));
		selectBox.setBorder(new Color(0,0,255,80));
		selectBox.setBorderWidth(2f);
	}

	@Override
	protected void renderView(final Graphics g) {
		super.renderView(g);
		anwserBox.render(g);

		selectBox.y(anwserBox.y() + margin + selected*selectBox.height());
        selectBox.render(g);

        out.setLocation(anwserBox.x() + anwserBox.width()/2 - g.getFont().getWidth(getLongestOption())/2, anwserBox.y() + margin);
		for(final String option : options){
			out.write(option);
		}
	}

	private String getLongestOption(){
		String current = "";
		for(final String option : options){
			if(option.length() > current.length()){
				current = option;
			}
		}
		return current;
	}

	private void up(){
		selected = (selected + 1) % options.length;
	}
	private void down(){
		selected--;
		if(selected < 0){
			selected = options.length-1;
		}
	}
	@Override
	public void KeyDown(final Key key) {
		if(key.isUp()){
			up();
		}
		if(key.isDown()){
			down();
		}
	}
}
