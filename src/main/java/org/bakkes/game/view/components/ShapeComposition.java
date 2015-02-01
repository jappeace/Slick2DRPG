package org.bakkes.game.view.components;

import java.util.EnumMap;
import java.util.Map;

import org.bakkes.game.model.map.Direction;
import org.newdawn.slick.Graphics;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class ShapeComposition implements IShape{

	private IShape mainShape;
	private Map<Direction, IShape> composites = new EnumMap<>(Direction.class);

	@Inject
	public ShapeComposition(final Provider<NoShape> noShapeProvider){
		for(final Direction key : Direction.values()){
			composites.put(key, noShapeProvider.get());
		}
	}

	/**
	 * this method has to be called
	 * @param shape
	 */
	public void setMainShape(final IShape shape){
		this.mainShape = shape;
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
		float totalWidth = mainShape.width();
		for(final Direction dir : new Direction[]{Direction.West, Direction.East}){
            totalWidth += composites.get(dir).width();
		}
		return totalWidth;
	}

	@Override
	public float height() {
		float totalHeight = mainShape.height();
		for(final Direction dir : new Direction[]{Direction.South, Direction.North}){
            totalHeight += composites.get(dir).height();
		}
		return totalHeight;
	}
	@Override
	public float x() {
		return mainShape.x() - composites.get(Direction.West).width();
	}

	@Override
	public float y() {
		return mainShape.y() - composites.get(Direction.North).height();
	}

	@Override
	public void render(final Graphics g) {
		mainShape.render(g);
	}


	@Override
	public void x(final float to) {
		mainShape.x(to);
		composites.get(Direction.West).x(x());
		composites.get(Direction.East).x(mainShape.x() + mainShape.width());
		for(final Direction key : new Direction[]{Direction.North, Direction.South}){
			composites.get(key).x(to);
		}
	}

	@Override
	public void y(final float to) {
		mainShape.y(to);
		composites.get(Direction.North).y(y());
		composites.get(Direction.South).y(mainShape.y() + mainShape.height());
		for(final Direction key : new Direction[]{Direction.West, Direction.East}){
			composites.get(key).y(to);
		}
	}

}
