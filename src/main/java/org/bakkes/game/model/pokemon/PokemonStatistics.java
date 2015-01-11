package org.bakkes.game.model.pokemon;

import java.util.Random;


public class PokemonStatistics implements IPokemonStatistics {
	int health;
	int attack;
	int defence;
	int speed;
	public PokemonStatistics(){
         health = 10;
         attack = 1;
         defence = 1;
         speed = 1;
	}
	public PokemonStatistics(final IPokemonStatistics stats){
		health = stats.getHealth();
		attack = stats.getAttack();
		defence = stats.getDefence();
		speed = stats.getSpeed();
	}
	/* (non-Javadoc)
	 * @see org.bakkes.game.model.pokemon.IPokemonStatistics#getHealth()
	 */
	@Override
	public final int getHealth() {
		return health;
	}
	/* (non-Javadoc)
	 * @see org.bakkes.game.model.pokemon.IPokemonStatistics#getAttack()
	 */
	@Override
	public final int getAttack() {
		return attack;
	}
	/* (non-Javadoc)
	 * @see org.bakkes.game.model.pokemon.IPokemonStatistics#getDefence()
	 */
	@Override
	public final int getDefence() {
		return defence;
	}
	/* (non-Javadoc)
	 * @see org.bakkes.game.model.pokemon.IPokemonStatistics#getSpeed()
	 */
	@Override
	public final int getSpeed() {
		return speed;
	}
	public final void setHealth(final int health) {
		this.health = health;
	}
	public final void setAttack(final int attack) {
		this.attack = attack;
	}
	public final void setDefence(final int defence) {
		this.defence = defence;
	}
	public final void setSpeed(final int speed) {
		this.speed = speed;
	}
	@Override
	public IPokemonStatistics plus(final IPokemonStatistics rightHand) {
		final PokemonStatistics result = new PokemonStatistics(this);
		result.attack += rightHand.getAttack();
		result.defence += rightHand.getDefence();
		result.speed += rightHand.getSpeed();
		result.health += rightHand.getHealth();
		return result;
	}
	@Override
	public IPokemonStatistics createFrom(final Random random) {
		final PokemonStatistics result = new PokemonStatistics();
        result.health += random.nextInt(this.health);
        result.defence += random.nextInt(this.defence);
        result.attack += random.nextInt(this.attack);
        result.speed += random.nextInt(this.speed);
		return result;
	}
	@Override
	public IPokemonStatistics minus(final IPokemonStatistics rightHand) {
		final PokemonStatistics result = new PokemonStatistics(this);
		result.attack -= rightHand.getAttack();
		result.defence -= rightHand.getDefence();
		result.speed -= rightHand.getSpeed();
		result.health -= rightHand.getHealth();
		return result;
	}
}
