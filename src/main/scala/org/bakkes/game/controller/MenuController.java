package org.bakkes.game.controller;

import com.google.inject.Inject;
import org.bakkes.game.controller.state.event.IMenuHandler;
import org.bakkes.game.controller.state.event.input.EmptyListener;
import org.bakkes.game.controller.state.event.input.IKeyListener;
import org.bakkes.game.controller.state.event.input.Key;
import org.bakkes.game.controller.state.CommonGameState;
import org.bakkes.game.view.components.Menu;

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
	@Override
	public void KeyUp(final Key key) {
		// TODO Auto-generated method stub

	}
	@Override
	public void update(final int delta) {
		// TODO Auto-generated method stub

	}
}
