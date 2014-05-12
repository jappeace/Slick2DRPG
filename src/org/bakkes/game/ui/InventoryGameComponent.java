package org.bakkes.game.ui;

import org.bakkes.game.entity.Player;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class InventoryGameComponent implements DrawableGameComponent {
	private Player player;
	
	public InventoryGameComponent(Player player) {
		this.player = player;
	}
	
	public void Render(GameContainer gc, Graphics g) {
		g.setColor(Color.white);
		g.fillRect(400, 100, 150, 300);
		g.setLineWidth(5f);
		g.setColor(Color.black);
		g.drawRect(400, 100, 150, 300);
		g.resetLineWidth();
	}

}
