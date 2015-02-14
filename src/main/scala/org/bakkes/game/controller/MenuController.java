package org.bakkes.game.controller;

import org.bakkes.game.controller.event.IMenuHandler;
import org.bakkes.game.controller.event.input.EmptyListener;
import org.bakkes.game.controller.event.input.IKeyListener;
import org.bakkes.game.controller.event.input.Key;
import org.bakkes.game.controller.state.CommonGameState;
import org.bakkes.game.view.components.Menu;

import com.google.inject.Inject;

public class MenuController implements IController{

	private @Inject CommonGameState state;
	private Menu menu;
	private IMenuHandler handler;
	private IKeyListener onKeyDown = new EmptyListener();

	public void set(final Menu menu, final IMenuHandler handler){
		this.menu = menu;
		this.handler = handler;
		menu.add(handler.getOptions());
		state.setOverlay(menu);
	}
	@Override
	public void KeyDown(final Key key) {
		onKeyDown.KeyDown(key);
		if(key.isDown()){
			menu.down();
		}
		if(key.isUp()){
			menu.up();
		}
		if(key.isConfirm()){
			handler.select(menu.getSelected());
		}
	}
	public void close(){
        state.setKeyListener(null);
        state.setOverlay(null);
	}
	public void setOnKeyDown(final IKeyListener onKeyDown) {
		this.onKeyDown = onKeyDown;
	}
}
