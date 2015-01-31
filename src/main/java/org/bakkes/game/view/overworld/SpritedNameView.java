package org.bakkes.game.view.overworld;

import org.bakkes.game.R;
import org.bakkes.game.model.IHasSpriteName;
import org.bakkes.game.model.ImageCache;
import org.bakkes.game.view.APositionedView;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.util.Log;

import com.google.inject.Inject;

public class SpritedNameView extends APositionedView{

	private IHasSpriteName item;

	private Image image;

	private @Inject ImageCache images;

	public void setItem(final IHasSpriteName to){
		item = to;
        final String path =R.itemSprites + item.getSpriteName() + ".png";
        try {
            image = images.load(path);
        } catch (final SlickException e) {
            Log.warn("failed drwaing " + path);
        }
	}

	@Override
	protected void renderView(final Graphics g) {
        g.drawImage(image, x(), y());
        out.write(item.getName());
        g.drawString(item.getName(), x() + image.getWidth(), y());
	}

}
