package org.bakkes.game.state.minigames.bird.entity.behavior;

import org.bakkes.game.math.Vector2;

public interface IBehavior {
	Vector2 calculate(Vector2 target);
}
