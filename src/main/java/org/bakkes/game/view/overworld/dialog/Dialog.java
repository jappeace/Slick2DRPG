package org.bakkes.game.view.overworld.dialog;

import org.bakkes.game.controller.input.IKeyListener;
import org.bakkes.game.controller.input.Key;
import org.bakkes.game.model.font.MutableFont;
import org.bakkes.game.view.components.Menu;
import org.newdawn.slick.Graphics;

import com.google.inject.Inject;

public class Dialog extends MessageBox implements IKeyListener{

	private String[] options = new String[]{"no", "yes"};
    private Menu menu;
	@Inject
	public Dialog(final Menu menu, final MutableFont font){
		super(font);
		this.menu = menu;
		menu.x(box.x()+ box.width()*0.8f - margin);
		menu.y(box.y()-margin);
		menu.add(options);
	}
	@Override
	protected void renderView(final Graphics g) {
		super.renderView(g);
		menu.render(g);
	}


	@Override
	public void KeyDown(final Key key) {
		if(key.isUp()){
			menu.up();
		}
		if(key.isDown()){
			menu.down();
		}
	}

	public int getSelected(){
		return menu.getSelected();
	}
}
