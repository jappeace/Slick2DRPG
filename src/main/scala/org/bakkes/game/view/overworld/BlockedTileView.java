package org.bakkes.game.view.overworld;

import com.google.inject.Inject;
import org.bakkes.game.model.map.BlockedTileTracker;
import org.bakkes.game.model.map.Tile;
import org.bakkes.game.view.AView;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class BlockedTileView extends AView{

	@Inject BlockedTileTracker tracker;

	@Override
	protected void renderView(final Graphics g) {
		g.setColor(new Color(255,0,0,50));
		for(final Tile t : tracker.findAllBlockedTiles()){
			g.fill(t.toShape());
		}
	}
}
