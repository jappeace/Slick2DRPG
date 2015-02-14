package org.bakkes.game.view.overworld.dialog;

import org.bakkes.game.controller.event.input.IKeyListener;
import org.bakkes.game.view.IRenderable;

public interface IMessageBox extends IRenderable, IKeyListener{

	public void setTitle(String title);

	public void setText(String text);

	public void setState(MessageBoxState to);
	public MessageBoxState getState();
}