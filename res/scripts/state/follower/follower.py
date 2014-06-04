from org.bakkes.game.entity.follower.state import IState

    
class FollowerState(IState):
    def enter(self, pokemon):
        print "Pokemon is now following player"
        
    def execute(self, pokemon):
        if pokemon.stepsTaken > 200: #pokemon took more than a hundred steps
            pokemon.getStateMachine().changeState(TiredState())
            
    def exit(self, pokemon):
        print "Leaving normal following state"
        
    def getName(self):
        return "Follower"