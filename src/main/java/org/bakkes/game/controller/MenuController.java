package org.bakkes.game.controller;

import org.bakkes.game.controller.input.Key;
import org.bakkes.game.controller.state.CommonGameState;
import org.bakkes.game.view.components.Menu;
import org.bakkes.game.view.overworld.MenuView;

import com.google.inject.Inject;

public class MenuController implements IController{

	private CommonGameState state;
	private Menu menu;
	@Inject
	public MenuController(final Menu menu, final MenuView view, final CommonGameState state){
		menu.add("pokemon", "items");
		view.setMenu(menu);
		this.menu = menu;
		this.state = state;
		state.setOverlay(view);
	}
	@Override
	public void KeyDown(final Key key) {
		if(key.isDown()){
			menu.down();
		}
		if(key.isUp()){
			menu.up();
		}
		if(key.isMenu()){
			sucicide();
		}
		if(key.isConfirm()){
			sucicide();
			// handle option
		}
	}
	private void sucicide(){
        state.setKeyListener(null);
        state.setOverlay(null);
	}

	@Override
	public void KeyUp(final Key key) {
	}

	@Override
	public void update(final int delta) {
	}

}
