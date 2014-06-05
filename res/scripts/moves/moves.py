from org.bakkes.game.battle import IMove
from org.bakkes.fuzzy import FuzzyVariable, FuzzyModule, DefuzzifyType
from org.bakkes.fuzzy.sets import RightShoulder

class Move(IMove):
    def __init__(self):
        return
    
class WaterMove(Move):
    
    def get_damage(self, entity, enemy):
        return 0
    
    def get_desirability(self, entity, enemy):
        self.fuzzy.fuzzify("waterstrength", entity.water_strength - enemy.water_strength)
        self.last_desirability = self.fuzzy.deFuzzify("desirability", DefuzzifyType.MAX_AV)
        return self.last_desirability
    
    def init_fuzzy(self):
        self.fuzzy = FuzzyModule()
        waterstrength = self.fuzzy.createFLV("waterstrength")
        water_weak = waterstrength.addLeftShoulderSet("move_weak", 0, 20, 33)
        water_ok = waterstrength.addTriangularSet("move_ok", 33, 50, 66)
        water_great = waterstrength.addRightShoulderSet("move_great", 66, 75, 100)
        
        desirability = self.fuzzy.createFLV("desirability")
        undesirable = waterstrength.addRightShoulderSet("undesirable", 66, 75, 100)
        desirable = waterstrength.addTriangularSet("desirable", 33, 50, 66)
        very_desirable = waterstrength.addLeftShoulderSet("very_desirable", 0, 20, 33)
        
        self.fuzzy.addRule(water_weak, undesirable)
        self.fuzzy.addRule(water_ok, desirable)
        self.fuzzy.addRule(water_great, very_desirable)
        
    def get_name(self):
        return "Water move"