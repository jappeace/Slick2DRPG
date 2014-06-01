import org.bakkes.game.entity.follower.state.State

def load_state(follower):
    follower.getStateMachine().changeState(FollowerState())
    
class FollowerState(State):
    def enter(self, pokemon):
        print "Pokemon is now following player"
        
    def execute(self, pokemon):
        if pokemon.hunger > 100:
            pokemon.getStateMachine().enterState(HungryState())
        else:
            print "Nothing is happening"
            pokemon.hunger = pokemon.hunger + 1
            
    def exit(self, pokemon):
        print "Leaving normal following state"
        
    def getName(self, pokemon):
        return "Follower"