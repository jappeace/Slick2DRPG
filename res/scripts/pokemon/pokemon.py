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
		
	def __default(self):
		self.level = 1 #NPC starting level
		self.type = PokemonType.NOT_SET
		self.health = 100 #every pokemon has 100 health by default
	
	def get_id(self):
		return self.id
		
	def get_name(self):
		return self.name
	
	def get_image(self):
		return self.id + ".png"
	
	def get_level(self):
		return level
	
	def get_type(self):
		if self.type is PokemonType.NOT_SET:
			raise "Pokemon type is not set"
		return self.type
	
	def get_desirability(self):
		raise "Desirability not implemented"
	
	def initialize_fuzzy(self):
		raise "Fuzzymodule not initialized"
	
class pokemon_0(Pokemon):
    def info(self):
        self.id = 0
        self.name = "Bulbasaur"
        
    def __custom_init(self):
    	print "wurks here"
    	
    def get_desirability(self):
    	return 0
    
    def initialize_fuzzy(self):
    	return 0
        
	
	
		