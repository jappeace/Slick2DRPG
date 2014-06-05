from org.bakkes.game.scripting.interfaces import IPokemon
from org.bakkes.game.scripting.interfaces import PokemonType
#from org.bakkes.fuzzy import *
#from org.bakkes.fuzzy.hedges import *
#from org.bakkes.fuzzy.operators import *
#from org.bakkes.fuzzy.sets import *

class Pokemon(IPokemon):

	def __init__(self):
		self.id = -1
		self.name = "undefined"
		
	def initialize(self):
		self.level = 1 #NPC starting level
		self.type = PokemonType.NOT_SET
		self.health = 100 #every pokemon has 100 health by default
		self.water_strength = 0
		self.earth_strength = 0
		self.fire_strength = 0
		self.moves = []
		
	def get_id(self):
		return self.id
		
	def get_name(self):
		return self.name
	
	def get_image(self):
		return str(self.id) + ".png"
	
	def get_level(self):
		return level
	
	def get_type(self):
		if self.type is PokemonType.NOT_SET:
			raise "Pokemon type is not set"
		return self.type
	
	def get_moves(self):
		return self.moves
	
	def get_earth_strength(self):
		return self.earth_strength
	
	def get_water_strength(self):
		return self.water_strength
	
	def get_fire_strength(self):
		return self.fire_strength
	
	def get_health(self):
		return self.health
	
	def get_desirability(self):
		raise "Desirability not implemented"
	
	def initialize_fuzzy(self):
		raise "Fuzzymodule not initialized"
	
class pokemon_0(Pokemon):
    def info(self):
		self.id = 1
		self.name = "Bulbasaur"
		self.earth_strength = 70
		self.water_strength = 30
		self.fire_strength = 10
		self.moves = [WaterMove(), EarthMove()]
        
class pokemon_1(Pokemon):
    def info(self):
		self.id = 2
		self.name = "Charmander"
		self.earth_strength = 30
		self.water_strength = 10
		self.fire_strength = 70
		self.moves = [FireMove(), EarthMove()]
		
class pokemon_2(Pokemon):
    def info(self):
		self.id = 3
		self.name = "Squirtle"
		self.earth_strength = 10
		self.water_strength = 70
		self.fire_strength = 30
		self.moves = [WaterMove(), EarthMove()]
        
	
		