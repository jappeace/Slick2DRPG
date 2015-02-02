package org.bakkes.game.view.overworld;

import org.bakkes.game.model.IHasSpriteName;
import org.bakkes.game.model.map.Direction;
import org.bakkes.game.view.APositionedView;
import org.bakkes.game.view.components.IShape;
import org.bakkes.game.view.components.ShapeComposition;
import org.bakkes.game.view.components.ShapePadding;
import org.bakkes.game.view.components.Sprite;
import org.bakkes.game.view.components.TextLine;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

import com.google.inject.Inject;

/**
 * ##SpritedNameView
 *
 * puts a sprite of a IHasSpriteName to the name, depending on the direction (default West (left))
 *
 * this is basicly a more concrete implementation of ShapeComposition. It figures out what to do
 * with values retrieved from IHasSpriteName
 *
 * the main purpouse of this class was to be used as menu item
 */
public class SpritedNameView extends APositionedView implements IShape{


	private @Inject TextLine line;
	private @Inject ShapeComposition composition;
	private @Inject Sprite sprite;
	private @Inject ShapePadding padding;

	private Direction direction = Direction.West;

	public void setNamed(final String spritePath, final IHasSpriteName to){
		line.setText(to.getName());
        final String path =spritePath + to.getSpriteName();
        sprite.setSpritePath(path);

        final float heightDifference = sprite.height() - line.height();
        padding.setPadding(new Vector2f(0, heightDifference/2));
        padding.setShape(line);
        composition.setShape(padding);

        composition.put(direction, sprite);
	}
	public void setDirection(final Direction to){
		direction = to;
	}

	@Override
	protected void renderView(final Graphics g) {
		composition.render(g);
	}
	@Override
	protected void onChangePosition(final Vector2f to){
		composition.x(to.x);
		composition.y(to.y);
	}
	@Override
	public float width() {
		return composition.width();
	}
	@Override
	public float height() {
		return composition.height();
	}
}
