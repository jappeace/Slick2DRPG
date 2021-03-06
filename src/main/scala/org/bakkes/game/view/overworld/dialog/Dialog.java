package org.bakkes.game.view.overworld.dialog;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.bakkes.game.controller.state.event.input.IKeyListener;
import org.bakkes.game.controller.state.event.input.Key;
import org.bakkes.game.model.font.MutableFont;
import org.bakkes.game.view.components.ITextableShape;
import org.bakkes.game.view.components.Menu;
import org.newdawn.slick.Graphics;

/**
 * adds a menu with choice to the messagebox
 */
public class Dialog extends MessageBox implements IKeyListener{

	private @Inject Provider<ITextableShape> textLineProvider;
    private Menu menu;
	@Inject
	public Dialog(final Menu menu, final MutableFont font){
		super(font);
		this.menu = menu;
		menu.x(box.x()+ box.width()*0.8f - margin);
		menu.y(box.y()-margin);
	}
	public void add(final String ... options){
		for(final String option : options){
			final ITextableShape t = textLineProvider.get();
			t.setText(option);
			menu.add(t);
		}
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
