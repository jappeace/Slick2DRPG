package org.bakkes.game.view.overworld;

import org.bakkes.game.view.AView;
import org.bakkes.game.view.components.Menu;
import org.newdawn.slick.Graphics;

/*
 * TODO: remove this maddness
 */
public class MenuView extends AView{

	private Menu menu;

	public void setMenu(final Menu menu){
		menu.x(550);
		menu.y(20);
		menu.width(180);
		menu.height(300);
		this.menu = menu;
	}

	@Override
	protected void renderView(final Graphics g) {
		menu.render(g);
	}
}
