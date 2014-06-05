from org.bakkes.game.battle import IMove
from org.bakkes.fuzzy import FuzzyVariable, FuzzyModule, DefuzzifyType
from org.bakkes.fuzzy.sets import RightShoulder
from math import fabs
from random import randrange

class Move(IMove):
    def __init__(self):
        return
    
class WaterMove(Move):
    
    def execute(self, entity, enemy):
        damage = (entity.water_strength - enemy.water_strength) / 4
        if(damage < 0): #if damage is very weak, pick random damage
            damage = randrange(1, 10)
        print "Damage: " + str(damage)
        enemy.health -= damage
    
    def get_desirability(self, entity, enemy):
        self.fuzzy.fuzzify("waterstrength", entity.water_strength - enemy.water_strength)
        self.last_desirability = self.fuzzy.deFuzzify("desirability", DefuzzifyType.MAX_AV)
        return self.last_desirability
    
    def init_fuzzy(self):
        self.fuzzy = FuzzyModule()
        waterstrength = self.fuzzy.createFLV("waterstrength")
        water_weak = waterstrength.addLeftShoulderSet("move_weak", -100, 20, 33)
        water_ok = waterstrength.addTriangularSet("move_ok", 33, 50, 66)
        water_great = waterstrength.addRightShoulderSet("move_great", 66, 75, 100)
        
        desirability = self.fuzzy.createFLV("desirability")
        very_desirable = desirability.addRightShoulderSet("very_desirable", 66, 75, 100)
        desirable = desirability.addTriangularSet("desirable", 33, 50, 66)
        undesirable = desirability.addLeftShoulderSet("undesirable", 0, 20, 33)
        
        self.fuzzy.addRule(water_weak, undesirable)
        self.fuzzy.addRule(water_ok, desirable)
        self.fuzzy.addRule(water_great, very_desirable)
        
    def get_name(self):
        return "Water move"
    
class EarthMove(Move):
    
    def execute(self, entity, enemy):
        damage = (entity.earth_strength - enemy.earth_strength) / 4
        if(damage < 0): #if damage is very weak, pick random damage
            damage = randrange(1, 10)
        print "Damage: " + str(damage)
        enemy.health -= damage
    
    def get_desirability(self, entity, enemy):
        self.fuzzy.fuzzify("earthstrength", entity.earth_strength - enemy.earth_strength)
        self.last_desirability = self.fuzzy.deFuzzify("desirability", DefuzzifyType.MAX_AV)
        return self.last_desirability
    
    def init_fuzzy(self):
        self.fuzzy = FuzzyModule()
        earthstrength = self.fuzzy.createFLV("earthstrength")
        earth_weak = earthstrength.addLeftShoulderSet("move_weak", -100, 20, 33)
        earth_ok = earthstrength.addTriangularSet("move_ok", 33, 50, 66)
        earth_great = earthstrength.addRightShoulderSet("move_great", 66, 75, 100)
        
        desirability = self.fuzzy.createFLV("desirability")
        very_desirable = desirability.addRightShoulderSet("very_desirable", 66, 75, 100)
        desirable = desirability.addTriangularSet("desirable", 33, 50, 66)
        undesirable = desirability.addLeftShoulderSet("undesirable", 0, 20, 33)
        
        self.fuzzy.addRule(earth_weak, undesirable)
        self.fuzzy.addRule(earth_ok, desirable)
        self.fuzzy.addRule(earth_great, very_desirable)
        
    def get_name(self):
        return "Earth move"
    
class FireMove(Move):
    
    def execute(self, entity, enemy):
        damage = (entity.fire_strength - enemy.fire_strength) / 4
        if(damage < 0): #if damage is very weak, pick random damage
            damage = randrange(1, 10)
        print "Damage: " + str(damage)
        enemy.health -= damage
    
    def get_desirability(self, entity, enemy):
        self.fuzzy.fuzzify("firestrength", entity.fire_strength - enemy.fire_strength)
        self.last_desirability = self.fuzzy.deFuzzify("desirability", DefuzzifyType.MAX_AV)
        return self.last_desirability
    
    def init_fuzzy(self):
        self.fuzzy = FuzzyModule()
        firestrength = self.fuzzy.createFLV("firestrength")
        fire_weak = firestrength.addLeftShoulderSet("move_weak", -100, 20, 33)
        fire_ok = firestrength.addTriangularSet("move_ok", 33, 50, 66)
        fire_great = firestrength.addRightShoulderSet("move_great", 66, 75, 100)
        
        desirability = self.fuzzy.createFLV("desirability")
        very_desirable = desirability.addRightShoulderSet("very_desirable", 66, 75, 100)
        desirable = desirability.addTriangularSet("desirable", 33, 50, 66)
        undesirable = desirability.addLeftShoulderSet("undesirable", 0, 20, 33)
        
        self.fuzzy.addRule(fire_weak, undesirable)
        self.fuzzy.addRule(fire_ok, desirable)
        self.fuzzy.addRule(fire_great, very_desirable)
        
    def get_name(self):
        return "Fire move"
    
class Weaken(Move):

    def execute(self, entity, enemy):
        return
    
    def get_desirability(self, entity, enemy):
        return 0.0
        self.fuzzy.fuzzify("firestrength", entity.fire_strength - enemy.fire_strength)
        self.last_desirability = self.fuzzy.deFuzzify("desirability", DefuzzifyType.MAX_AV)
        return self.last_desirability
    
    def init_fuzzy(self):
        return
        self.fuzzy = FuzzyModule()
        firestrength = self.fuzzy.createFLV("firestrength")
        fire_weak = firestrength.addLeftShoulderSet("move_weak", -100, 20, 33)
        fire_ok = firestrength.addTriangularSet("move_ok", 33, 50, 66)
        fire_great = firestrength.addRightShoulderSet("move_great", 66, 75, 100)
        
        desirability = self.fuzzy.createFLV("desirability")
        very_desirable = desirability.addRightShoulderSet("very_desirable", 66, 75, 100)
        desirable = desirability.addTriangularSet("desirable", 33, 50, 66)
        undesirable = desirability.addLeftShoulderSet("undesirable", 0, 20, 33)
        
        self.fuzzy.addRule(fire_weak, undesirable)
        self.fuzzy.addRule(fire_ok, desirable)
        self.fuzzy.addRule(fire_great, very_desirable)
        
    def get_name(self):
        return "Weaken"