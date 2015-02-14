package org.bakkes.game.controller.state.overworld.command;

import org.bakkes.game.model.entity.IOverworldEntity;
import org.bakkes.game.model.map.Tile;

public class Teleport extends AOneTimeCommand{

	private Tile to;
	private IOverworldEntity subject;
	@Override
	public void executeOnce(final float tpf) {
		subject.setPosition(to);
	}
	public final Teleport setTo(final Tile to) {
		this.to = to;
		return this;
	}
	public final Teleport setSubject(final IOverworldEntity subject) {
		this.subject = subject;
		return this;
	}
}
