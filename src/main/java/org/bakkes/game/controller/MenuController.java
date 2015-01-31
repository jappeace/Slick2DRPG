package org.bakkes.game.controller;

import org.bakkes.game.controller.event.IMenuHandler;
import org.bakkes.game.controller.event.input.Key;
import org.bakkes.game.controller.state.CommonGameState;
import org.bakkes.game.view.components.Menu;
import org.bakkes.game.view.overworld.MenuView;

import com.google.inject.Inject;

public class MenuController extends AController implements IController{

	private CommonGameState state;
	private Menu menu;
	private IMenuHandler handler;
	@Inject
	public MenuController(final Menu menu, final MenuView view, final CommonGameState state){
		view.setMenu(menu);
		this.menu = menu;
		this.state = state;
		state.setOverlay(view);
	}
	public void setItemSelectHandler(final IMenuHandler handler){
		this.handler = handler;
		menu.add(handler.getOptions());
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
			handler.select(menu.getSelected());
		}
	}
	private void sucicide(){
        state.setKeyListener(null);
        state.setOverlay(null);
	}
}
