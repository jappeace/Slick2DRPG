package org.bakkes.game.controller.init;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.bakkes.game.controller.init.scripting.dsl.area.PlayerDsl;
import org.bakkes.game.controller.init.scripting.loader.ScriptLoader;
import org.bakkes.game.model.entity.player.Player;
import org.bakkes.game.model.map.Tile;

import java.nio.file.Path;

public class PlayerLoader {
	private @Inject ScriptLoader loader;
	private @Inject PlayerDsl dsl;
	private @Inject @Named("overworld") Path path;

	private @Inject Player player;
	private Tile startLocation;
	public void load(){
		loader.load(path.resolve("player.dsl"), dsl);
		setStartLocation(player.getTile());
	}
	public Tile getStartLocation() {
		return startLocation;
	}
	private void setStartLocation(final Tile startLocation) {
		this.startLocation = startLocation;
	}
}
