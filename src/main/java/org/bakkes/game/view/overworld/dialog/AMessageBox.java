package org.bakkes.game.view.overworld.dialog;

import org.bakkes.game.controller.event.input.Key;
import org.bakkes.game.view.AView;

import com.google.common.base.Preconditions;

public abstract class AMessageBox extends AView implements IMessageBox{
	private String title = "";
	private String text = "";
	private MessageBoxState state = MessageBoxState.Created;

	/* (non-Javadoc)
	 * @see org.bakkes.game.view.overworld.dialog.IDialog#setTitle(java.lang.String)
	 */
	@Override
	public final void setTitle(final String title) {
		this.title = Preconditions.checkNotNull(title, "");
	}

	/* (non-Javadoc)
	 * @see org.bakkes.game.view.overworld.dialog.IDialog#setText(java.lang.String)
	 */
	@Override
	public void setText(final String text) {
		this.text = Preconditions.checkNotNull(text, "");
	}

	protected String getText(){
		return text;
	}
	protected String getTitle(){
		return title;
	}

	public void queued() {
	}
	public void show() {
	}
	public void done() {
	}

	/**
	 * the states are my little syncrhonization trick
	 */
	@Override
	public synchronized void setState(final MessageBoxState to) {
		this.state = to;
		switch(to){
		case Showing:
			show();
			break;
		case Queued:
			queued();
			break;
		case Done:
			done();
			break;
		}
	}

	@Override
	public synchronized MessageBoxState getState() {
		return state;
	}

	@Override
	public void KeyDown(final Key key) {}

	@Override
	public void KeyUp(final Key key) {}

}
