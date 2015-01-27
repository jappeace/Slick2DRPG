package org.bakkes.game.view.overworld.dialog;

import org.bakkes.game.controller.state.CommonGameState;
import org.bakkes.game.view.AView;
import org.newdawn.slick.util.Log;

import com.google.common.base.Preconditions;
import com.google.inject.Inject;

public abstract class AMessageBox extends AView implements IMessageBox{
	private @Inject CommonGameState container;

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

	/* (non-Javadoc)
	 * @see org.bakkes.game.view.overworld.dialog.IDialog#show()
	 */
	@Override
	public void show() {
		Log.info("showing dialogbox with: " + text);
		container.queueDialogBox(this);
	}

	@Override
	public void setState(final MessageBoxState to) {
		this.state = to;
	}

	@Override
	public MessageBoxState getState() {
		return state;
	}

}
