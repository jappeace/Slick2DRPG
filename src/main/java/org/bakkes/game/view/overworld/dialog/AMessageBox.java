package org.bakkes.game.view.overworld.dialog;

import org.bakkes.game.controller.input.Key;
import org.bakkes.game.view.AView;
import org.newdawn.slick.util.Log;

import com.google.common.base.Preconditions;

public abstract class AMessageBox extends AView implements IMessageBox{
	private String title = "";
	private String text = "";
	private MessageBoxState state;

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
		Log.info("queing dialogbox " + title);
	}
	public void show() {
		Log.info("showing dialogbox " + title);
	}
	public void done() {
		Log.info("done with dialogbox " + title);
	}

	@Override
	public void setState(final MessageBoxState to) {
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
	public MessageBoxState getState() {
		return state;
	}

	@Override
	public void KeyDown(final Key key) {}

	@Override
	public void KeyUp(final Key key) {}

}