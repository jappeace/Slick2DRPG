import org.bakkes.game.entity.follower.state.State

class HungryState(State):
    def enter(self, pokemon):
        print "Pokemon is now following player"
        
    def execute(self, pokemon):
        if pokemon.hunger <= 0:
            pokemon.getStateMachine().enterState(FollowingPokemon)
        else:
            print "Pokemon is hungry and eating at the moment"
            pokemon.hunger = pokemon.hunger - 1
            
    def exit(self, pokemon):
        print "Leaving normal following state"
        
    def getName(self, pokemon):
        return "Hunger"