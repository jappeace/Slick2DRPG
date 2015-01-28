package org.bakkes.game.view.overworld.dialog;

import org.bakkes.game.controller.input.IKeyListener;
import org.bakkes.game.controller.state.CommonGameState;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import com.google.inject.Inject;

public class Dialog extends MessageBox implements IKeyListener{

	private String[] options = new String[]{"yes", "no"};

	private @Inject CommonGameState container;
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

	@Override
	public void show(){
		super.show();
		container.add(this);
	}

	@Override
	public void KeyDown(final int key, final char c) {
		switch(key){
		case Keyboard.KEY_UP:
			up();
			return;
		case Keyboard.KEY_W:
			up();
			return;
		case Keyboard.KEY_DOWN:
			down();
			return;
		case Keyboard.KEY_S:
			down();
		case Keyboard.KEY_RETURN:
			done();
		}
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
	public void done(){
		container.remove(this); // cleanup
		// send result back
	}
	@Override
	public void KeyUp(final int key, final char c) {
	}
}
