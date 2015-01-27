package org.bakkes.game.view.overworld.dialog;

import org.bakkes.game.view.IRenderable;

public interface IMessageBox extends IRenderable{

	public abstract void setTitle(String title);

	public abstract void setText(String text);

	public abstract void show();

	public void setState(MessageBoxState to);
	public MessageBoxState getState();
}