package org.bakkes.game.controller;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

import org.bakkes.game.view.components.IShape;
import org.bakkes.game.view.components.ITextableShape;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class ShapesConverter {

	private @Inject Provider<ITextableShape> textLineProvider;
	public Collection<IShape> convert(final String ... strings){
		return convert(Arrays.asList(strings));
	}
	public Collection<IShape> convert(final Iterable<String> strings){
		final Collection<IShape> result = new LinkedList<>();
		for(final String string: strings){
			final ITextableShape t = textLineProvider.get();
			t.setText(string);
			result.add(t);
		}
		return result;
	}
}
