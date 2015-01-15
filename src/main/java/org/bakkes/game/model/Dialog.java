package org.bakkes.game.model;

import com.google.common.base.Preconditions;

public class Dialog implements IModel{
	private String title = "";
	private String text = "";

	public final String getTitle() {
		return title;
	}
	public final String getText() {
		return text;
	}
	public final void setTitle(final String title) {
		this.title = Preconditions.checkNotNull(title, "");
	}
	public final void setText(final String text) {
		this.text = Preconditions.checkNotNull(text, "");
	}
	@Override
	public String getName() {
		return getTitle();
	}
}
