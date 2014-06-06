from org.bakkes.game.battle import IMove
from org.bakkes.fuzzy import FuzzyVariable, FuzzyModule, DefuzzifyType
from org.bakkes.fuzzy.sets import RightShoulder
from org.bakkes.fuzzy.operators import FuzzyAnd
from org.bakkes.fuzzy.hedges import FuzzyFairly, FuzzyVery
from math import fabs
from random import randrange

class Move(IMove):
    def __init__(self):
        return
    
class WaterMove(Move):
    
    def execute(self, entity, enemy):
        damage = (entity.water_strength - enemy.water_strength) / 4
        if(damage <= 0): #if damage is very weak, pick random damage
            damage = randrange(1, 10)
        else:
            damage = randrange(1, damage) #take random from damage
        print "Damage: " + str(damage)
        enemy.health -= damage
    
    def get_desirability(self, entity, enemy):
        self.fuzzy.fuzzify("waterstrength", entity.water_strength - enemy.water_strength)
        self.last_desirability = self.fuzzy.deFuzzify("desirability", DefuzzifyType.MAX_AV)
        return self.last_desirability
    
    def init_fuzzy(self):
        self.fuzzy = FuzzyModule()
        waterstrength = self.fuzzy.createFLV("waterstrength")
        water_weak = waterstrength.addLeftShoulderSet("move_weak", -100, 20, 40)
        water_ok = waterstrength.addTriangularSet("move_ok", 20, 40, 75)
        water_great = waterstrength.addRightShoulderSet("move_great", 40, 75, 100)
        
        desirability = self.fuzzy.createFLV("desirability")
        very_desirable = desirability.addRightShoulderSet("very_desirable", 50, 75, 100)
        desirable = desirability.addTriangularSet("desirable", 25, 50, 75)
        undesirable = desirability.addLeftShoulderSet("undesirable", 0, 25, 50)
        
        self.fuzzy.addRule(water_weak, undesirable)
        self.fuzzy.addRule(water_ok, desirable)
        self.fuzzy.addRule(water_great, very_desirable)
        
    def get_name(self):
        return "Water move"
    
class EarthMove(Move):
    
    def execute(self, entity, enemy):
        damage = (entity.earth_strength - enemy.earth_strength) / 4
        if(damage <= 0): #if damage is very weak, pick random damage
            damage = randrange(1, 10)
        else:
            damage = randrange(1, damage) #take random from damage
        print "Damage: " + str(damage)
        enemy.health -= damage
    
    def get_desirability(self, entity, enemy):
        self.fuzzy.fuzzify("earthstrength", entity.earth_strength - enemy.earth_strength)
        self.last_desirability = self.fuzzy.deFuzzify("desirability", DefuzzifyType.MAX_AV)
        return self.last_desirability
    
    def init_fuzzy(self):
        self.fuzzy = FuzzyModule()
        earthstrength = self.fuzzy.createFLV("earthstrength")
        earth_weak = earthstrength.addLeftShoulderSet("move_weak", -100, 20, 40)
        earth_ok = earthstrength.addTriangularSet("move_ok", 20, 40, 75)
        earth_great = earthstrength.addRightShoulderSet("move_great", 40, 75, 100)
        
        desirability = self.fuzzy.createFLV("desirability")
        very_desirable = desirability.addRightShoulderSet("very_desirable", 50, 75, 100)
        desirable = desirability.addTriangularSet("desirable", 25, 50, 75)
        undesirable = desirability.addLeftShoulderSet("undesirable", 0, 25, 50)
        
        self.fuzzy.addRule(earth_weak, undesirable)
        self.fuzzy.addRule(earth_ok, desirable)
        self.fuzzy.addRule(earth_great, very_desirable)
        
    def get_name(self):
        return "Earth move"
    
class FireMove(Move):
    
    def execute(self, entity, enemy):
        damage = (entity.fire_strength - enemy.fire_strength) / 4
        if(damage <= 0): #if damage is very weak, pick random damage
            damage = randrange(1, 10)
        else:
            damage = randrange(1, damage) #take random from damage
        print "Damage: " + str(damage)
        enemy.health -= damage
    
    def get_desirability(self, entity, enemy):
        self.fuzzy.fuzzify("firestrength", entity.fire_strength - enemy.fire_strength)
        self.last_desirability = self.fuzzy.deFuzzify("desirability", DefuzzifyType.MAX_AV)
        return self.last_desirability
    
    def init_fuzzy(self):
        self.fuzzy = FuzzyModule()
        firestrength = self.fuzzy.createFLV("firestrength")
        fire_weak = firestrength.addLeftShoulderSet("move_weak", -100, 20, 40)
        fire_ok = firestrength.addTriangularSet("move_ok", 20, 40, 75)
        fire_great = firestrength.addRightShoulderSet("move_great", 40, 75, 100)
        
        desirability = self.fuzzy.createFLV("desirability")
        very_desirable = desirability.addRightShoulderSet("very_desirable", 50, 75, 100)
        desirable = desirability.addTriangularSet("desirable", 25, 50, 75)
        undesirable = desirability.addLeftShoulderSet("undesirable", 0, 25, 50)
        
        self.fuzzy.addRule(fire_weak, undesirable)
        self.fuzzy.addRule(fire_ok, desirable)
        self.fuzzy.addRule(fire_great, very_desirable)
        
    def get_name(self):
        return "Fire move"
    
class FireWeaken(Move):
    
    def execute(self, entity, enemy):
        enemy.set_fire_strength(enemy.get_fire_strength() - randrange(5, 15))
    
    def get_desirability(self, entity, enemy):
        self.fuzzy.fuzzify("fireresistance", enemy.fire_strength)
        self.fuzzy.fuzzify("enemyhealth", enemy.health)
        self.last_desirability = self.fuzzy.deFuzzify("desirability", DefuzzifyType.MAX_AV)
        return self.last_desirability
    
    def init_fuzzy(self):
        self.fuzzy = FuzzyModule()
        
        weakenfirestrength = self.fuzzy.createFLV("fireresistance")
        weaken_fire_poor = weakenfirestrength.addLeftShoulderSet("move_fire_poor", 0, 22, 44)
        weaken_fire_ok = weakenfirestrength.addTriangularSet("move_fire_ok", 22, 44, 77)
        weaken_fire_great = weakenfirestrength.addRightShoulderSet("move_fire_great", 44, 77, 100)
        
        enemyhealth = self.fuzzy.createFLV("enemyhealth")
        health_poor = enemyhealth.addLeftShoulderSet("health_poor", 0, 40, 50)
        health_ok = enemyhealth.addTriangularSet("health_ok", 40, 50, 75)
        health_great = enemyhealth.addRightShoulderSet("health_ok", 50, 75, 100)
        
        desirability = self.fuzzy.createFLV("desirability")
        very_desirable = desirability.addRightShoulderSet("very_desirable", 50, 75, 100)
        desirable = desirability.addTriangularSet("desirable", 25, 50, 75)
        undesirable = desirability.addLeftShoulderSet("undesirable", 0, 25, 50)
        
        self.fuzzy.addRule(FuzzyAnd(weaken_fire_poor, health_poor), FuzzyVery(undesirable))
        self.fuzzy.addRule(FuzzyAnd(weaken_fire_poor, health_ok), FuzzyFairly(undesirable))
        self.fuzzy.addRule(FuzzyAnd(weaken_fire_poor, health_great), undesirable)
        
        
        self.fuzzy.addRule(FuzzyAnd(weaken_fire_ok, health_poor), FuzzyFairly(undesirable))
        self.fuzzy.addRule(FuzzyAnd(weaken_fire_ok, health_ok), undesirable)
        self.fuzzy.addRule(FuzzyAnd(weaken_fire_ok, health_great), desirable)
        
        self.fuzzy.addRule(FuzzyAnd(weaken_fire_great, health_poor), desirable)
        self.fuzzy.addRule(FuzzyAnd(weaken_fire_great, health_ok), FuzzyFairly(desirable))
        self.fuzzy.addRule(FuzzyAnd(weaken_fire_great, health_great), FuzzyVery(desirable))
        
    def get_name(self):
        return "Fire weaken"
    
class WaterWeaken(Move):
    
    def execute(self, entity, enemy):
        enemy.set_water_strength(enemy.get_water_strength() - randrange(5, 15))
    
    def get_desirability(self, entity, enemy):
        self.fuzzy.fuzzify("waterresistance", enemy.water_strength)
        self.fuzzy.fuzzify("enemyhealth", enemy.health)
        self.last_desirability = self.fuzzy.deFuzzify("desirability", DefuzzifyType.MAX_AV)
        return self.last_desirability
    
    def init_fuzzy(self):
        self.fuzzy = FuzzyModule()
        
        weakenwaterstrength = self.fuzzy.createFLV("waterresistance")
        weaken_water_poor = weakenwaterstrength.addLeftShoulderSet("move_water_poor", 0, 22, 44)
        weaken_water_ok = weakenwaterstrength.addTriangularSet("move_water_ok", 22, 44, 77)
        weaken_water_great = weakenwaterstrength.addRightShoulderSet("move_water_great", 44, 77, 100)
        
        enemyhealth = self.fuzzy.createFLV("enemyhealth")
        health_poor = enemyhealth.addLeftShoulderSet("health_poor", 0, 40, 50)
        health_ok = enemyhealth.addTriangularSet("health_ok", 40, 50, 75)
        health_great = enemyhealth.addRightShoulderSet("health_ok", 50, 75, 100)
        
        desirability = self.fuzzy.createFLV("desirability")
        very_desirable = desirability.addRightShoulderSet("very_desirable", 50, 75, 100)
        desirable = desirability.addTriangularSet("desirable", 25, 50, 75)
        undesirable = desirability.addLeftShoulderSet("undesirable", 0, 25, 50)
        
        self.fuzzy.addRule(FuzzyAnd(weaken_water_poor, health_poor), FuzzyVery(undesirable))
        self.fuzzy.addRule(FuzzyAnd(weaken_water_poor, health_ok), FuzzyFairly(undesirable))
        self.fuzzy.addRule(FuzzyAnd(weaken_water_poor, health_great), FuzzyFairly(undesirable))
        
        
        self.fuzzy.addRule(FuzzyAnd(weaken_water_ok, health_poor), FuzzyFairly(undesirable))
        self.fuzzy.addRule(FuzzyAnd(weaken_water_ok, health_ok), undesirable)
        self.fuzzy.addRule(FuzzyAnd(weaken_water_ok, health_great), desirable)
        
        self.fuzzy.addRule(FuzzyAnd(weaken_water_great, health_poor), desirable)
        self.fuzzy.addRule(FuzzyAnd(weaken_water_great, health_ok), FuzzyFairly(desirable))
        self.fuzzy.addRule(FuzzyAnd(weaken_water_great, health_great), FuzzyVery(desirable))
        
    def get_name(self):
        return "Water weaken"