package org.bakkes.game.state.controller;

import org.newdawn.slick.Graphics;

public interface IController {
	void update(int tpf);
	void render(Graphics g);
}
