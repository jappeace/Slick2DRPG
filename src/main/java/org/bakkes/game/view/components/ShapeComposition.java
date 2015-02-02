package org.bakkes.game.view.components;

import java.util.EnumMap;
import java.util.Map;

import org.bakkes.game.model.map.Direction;
import org.newdawn.slick.Graphics;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class ShapeComposition extends AShapeContainer implements IShape{

	private Map<Direction, IShape> composites = new EnumMap<>(Direction.class);

	@Inject
	public ShapeComposition(final Provider<NoShape> noShapeProvider){
		for(final Direction key : Direction.values()){
			composites.put(key, noShapeProvider.get());
		}
	}

	public IShape put(final Direction relativeToMainshape, final IShape what){
		final IShape result = composites.put(relativeToMainshape, what);
		updateCoordinates();
		return result;
	}
	private void updateCoordinates(){
		// it just looks so weird
		x(x());
		y(y());
	}
	@Override
	public float width() {
		float totalWidth = getShape().width();
		// pick the biggest
		for(final Direction dir : new Direction[]{Direction.West, Direction.East}){
			final float compWidth = composites.get(dir).width();
			if(totalWidth < compWidth){
				totalWidth = compWidth;
			}
		}

		// add the other direction
		for(final Direction dir : new Direction[]{Direction.West, Direction.East}){
            totalWidth += composites.get(dir).width();
		}
		return totalWidth;
	}

	@Override
	public float height() {
		float totalHeight = getShape().height();

		// pick the biggest
		for(final Direction dir : new Direction[]{Direction.West, Direction.East}){
			final float compHeight = composites.get(dir).height();
			if(totalHeight < compHeight){
				totalHeight = compHeight;
			}
		}

		// add the other direction
		for(final Direction dir : new Direction[]{Direction.South, Direction.North}){
            totalHeight += composites.get(dir).height();
		}
		return totalHeight;
	}
	@Override
	public float x() {
		return getShape().x() - composites.get(Direction.West).width();
	}

	@Override
	public float y() {
		return getShape().y() - composites.get(Direction.North).height();
	}

	@Override
	public void render(final Graphics g) {
		getShape().render(g);
		for(final IShape shape : composites.values()){
			shape.render(g);
		}
	}


	@Override
	public void x(final float to) {
		getShape().x(to);
		composites.get(Direction.West).x(x());
		composites.get(Direction.East).x(getShape().x() + getShape().width());
		for(final Direction key : new Direction[]{Direction.North, Direction.South}){
			composites.get(key).x(to);
		}
	}

	@Override
	public void y(final float to) {
		getShape().y(to);
		composites.get(Direction.North).y(y());
		composites.get(Direction.South).y(getShape().y() + getShape().height());
		for(final Direction key : new Direction[]{Direction.West, Direction.East}){
			composites.get(key).y(to);
		}
	}


}
