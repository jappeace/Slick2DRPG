package org.bakkes.game.model.pokemon

import java.util.Random

import com.google.inject.Inject
import nl.jappieklooster.annotation.Nullable
import org.bakkes.game.model.AModel
import org.bakkes.game.model.battle.move.IMove
import org.newdawn.slick.util.Log

/**
 * this class represents a pokemon, since its a living thing, its mutable like crazy
 * all vars are private however, so mutation is limited to the various setters
 * @param species
 * @param random pokemon needs some randomness
 * @param normalStats the stats that are normal for his level
 */
class Pokemon(
	val species: IPokemonSpecies,
	@Nullable
	private var normalStats: IPokemonStatistics,
	private val random:Random
 ) extends AModel{

	if(species == null){
		throw new IllegalArgumentException("species cannot be null")
	}
	if(normalStats == null){
		normalStats = species.getBase
	}
	private var currentStats: PokemonStatistics = null
	private var level: Int = 0
	private var experiance: Int = 0
	val isFemale: Boolean = random.nextBoolean()
	val isShiny: Boolean = random.nextFloat() > 0.001f

	setName(species.getName)
	heal

	@Inject def this(species: IPokemonSpecies) {
		this(species, null, new Random())
	}

	/**
	 * level up to a specific level, this has all the user fuckup gaurds
	 * @param lvl
	 */
	def setLevel(lvl: Int) {
		if (lvl < 0) {
			throw new IllegalArgumentException("trying to level up to a negative level")
		}
		setExperiance(calculateXpFor(lvl))
		if (lvl > level) {
			Log.warn("leveling down?")
		}
		level(lvl - level)
		heal
	}

	private def level(diff: Int) {
		level = getLevel + diff
		val increase = new PokemonStatistics(species.getIncrease)
		var i = 0
		while (i < Math.abs(diff)) {
			val change = increase.copy(
				health = random.nextInt(increase.health),
				attack = random.nextInt(increase.attack),
				defence = random.nextInt(increase.defence),
				speed = random.nextInt(increase.speed)
			)
			if (diff > 0) {
				normalStats = normalStats.plus(change)
			}else {
				normalStats = normalStats.minus(change)
			}
			i += 1
		}
	}

	final def getSpecies: IPokemonSpecies = species

	def heal = currentStats = new PokemonStatistics(normalStats)

	final def damage(dmg: Int) {
		currentStats = currentStats.copy(health = currentStats.health-dmg)
	}

	def getMoves: Seq[IMove] = species.getMoves
	/**
	 * @param exp
	 * @return if levelup show difference
	 */
	def addExperiance(exp: Int): IPokemonStatistics = {
		if (exp < 0) {
			Log.warn("adding negative experiance?")
		}
		setExperiance(getExperiance + exp)
		val nextLevel: Int = calculateXpFor(level + 1)
		Log.info("current xp = " + getExperiance)
		Log.info("next level xp = " + nextLevel)
		if (getExperiance > nextLevel) {
			Log.info("level up!!")
			val current: PokemonStatistics = new PokemonStatistics(normalStats)
			level(1)
			return normalStats.minus(current).plus(addExperiance(0))
		}
		return null
	}

	def calculateXpFor(lvl: Int): Int = {
		return (4 * Math.pow(lvl, 3) / (4 + species.getTrainingSpeed)).toInt
	}

	final def getNormalStats: IPokemonStatistics = normalStats
	final def getCurrentStats: IPokemonStatistics = currentStats

	def isAlive: Boolean = currentStats.getHealth > 0
	def getLevel: Int = level

	def getExperiance: Int = experiance

	/**
	 * set the experiance and adjust the level without changing the statistics
	 * @param experiance
	 */
	def setExperiance(experiance: Int) {
		if (experiance < 0) {
			throw new IllegalArgumentException("negative experiance not suported, (yes this games gives only positive experiances)")
		}
		if (experiance < this.experiance) {
			Log.warn("setting experiance to a lower amount than before?")
		}
		this.experiance = experiance
	}

	override def toString: String = {
		return getName + ", current stats: [" + currentStats + "]"+
				" normal stats: [" + normalStats + "]"
	}
}