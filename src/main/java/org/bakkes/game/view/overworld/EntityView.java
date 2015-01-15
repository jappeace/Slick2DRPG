package org.bakkes.game.view.overworld;

import org.bakkes.game.R;
import org.bakkes.game.model.ImageCache;
import org.bakkes.game.model.entity.Entity;
import org.bakkes.game.model.map.Direction;
import org.bakkes.game.view.AView;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import com.google.inject.Inject;

public class EntityView extends AView{

	private SpriteSheet _spriteSheet;
	private Animation[] _animation;
	private Entity entity;

	@Inject
	public EntityView(final ImageCache cache){
		// probably good defaults, this should become configurable
        try {
            _spriteSheet = cache.load(R.sprites+"player.png", 32, 32);

            _animation = new Animation[4];
            _animation[Direction.NORTH] = new Animation(_spriteSheet, 0, 0, 2, 0, true, 200, true);
            _animation[Direction.EAST]  = new Animation(_spriteSheet, 0, 3, 2, 3, true, 200, true);
            _animation[Direction.SOUTH] = new Animation(_spriteSheet, 0, 1, 2, 1, true, 200, true);
            _animation[Direction.WEST]  = new Animation(_spriteSheet, 0, 2, 2, 2, true, 200, true);

            for(int i = 0; i <= _animation.length - 1; i++) {
                _animation[i].setPingPong(true);
                _animation[i].setAutoUpdate(false);
            }
        } catch (final SlickException e) {
            e.printStackTrace();
        }
	}
	@Override
	public void renderView(final Graphics g) {
		final Animation animation = _animation[getEntity().getFacing()];
		if(getEntity().isWalking()){
			animation.setAutoUpdate(true);
		}else{
			animation.setAutoUpdate(false);
			animation.setCurrentFrame(0);
		}
		animation.draw(getEntity().getPosition().getX(), getEntity().getPosition().getY());
	}
	private Entity getEntity() {
		return entity;
	}
	public void setEntity(final Entity entity) {
		this.entity = entity;
	}

}
