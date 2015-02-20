package org.bakkes.game.controller;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import nl.jappieklooster.annotation.Nullable;
import org.bakkes.game.controller.state.event.input.Key;
import org.bakkes.game.controller.state.CommonGameState;
import org.bakkes.game.view.overworld.dialog.IMessageBox;
import org.bakkes.game.view.overworld.dialog.MessageBoxState;

import java.util.LinkedList;
import java.util.Queue;

@Singleton
public class MessageBoxController implements IController{

	private @Inject Provider<CommonGameState> state;
	private @Inject Provider<IMessageBox> msgBoxProvider;
	private Queue<IMessageBox> dialogQueue = new LinkedList<>();
	private @Nullable IMessageBox currentMsgBox;

	private final void setOverlay(final IMessageBox overlay) {
		state.get().setOverlay(overlay);
		this.currentMsgBox = overlay;
	}

	public boolean add(final String title, final String text){
		final IMessageBox box = msgBoxProvider.get();
		box.setTitle(title);
		box.setText(text);
		return add(box);
	}
	public boolean add(final IMessageBox box){
		box.setState(MessageBoxState.Queued);
		return dialogQueue.add(box);
	}
	public void nextDialog() {
		if(currentMsgBox != null){
			currentMsgBox.setState(MessageBoxState.Done);
		}
		if(dialogQueue.size() > 0) {
			state.get().setKeyListener(this);
			setOverlay(dialogQueue.remove());
			currentMsgBox.setState(MessageBoxState.Showing);
			return;
		}
		state.get().setKeyListener(null);
		setOverlay(null);
	}

	@Override
	public void KeyDown(final Key k) {
		if(k.isConfirm()){
			nextDialog();
			return;
		}
		currentMsgBox.KeyDown(k);
	}

	@Override
	public void KeyUp(final Key k) {
		currentMsgBox.KeyUp(k);
	}

	@Override
	public void update(final int delta) {
        if(dialogQueue.isEmpty()){
			return;
		}
		if(currentMsgBox == null){
			nextDialog();
		}
	}
}
