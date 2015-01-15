package org.bakkes.game.view.overworld;

import org.bakkes.game.R;
import org.bakkes.game.model.entity.Entity;
import org.bakkes.game.model.map.Direction;
import org.bakkes.game.view.AView;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class EntityView extends AView{

	private SpriteSheet _spriteSheet;
	private Animation[] _animation;
	private Entity entity;

	public EntityView(final Entity entity){
		this.entity = entity;
		// probably good defaults, this should become configurable
        try {
            _spriteSheet = new SpriteSheet(R.sprites+"player.png", 32, 32);

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
		final Animation animation = _animation[entity.getFacing()];
		if(entity.isWalking()){
			animation.setAutoUpdate(true);
		}else{
			animation.setAutoUpdate(false);
			animation.setCurrentFrame(0);
		}
		animation.draw(entity.getPosition().getX(), entity.getPosition().getY());
	}

}
