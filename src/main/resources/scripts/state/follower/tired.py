from org.bakkes.game.entity.follower.state import IState


class TiredState(IState):
    def enter(self, pokemon):
        pokemon.getParent().showDialog("Pokemon is now tired, will try to eat something or faint after a few more steps")
        
    def execute(self, pokemon):
        inv = pokemon.getParent().getInventory()
        if inv.hasItem(4): #has item no. 4, protein
            inv.deleteItem(inv.getSlot(4)) #remove protein & can walk again
            pokemon.stepsTaken = 0
            pokemon.getParent().showDialog("Your pokemon ate the protein and can walk normally again!")
            pokemon.getStateMachine().changeState(FollowerState())
        if pokemon.stepsTaken > 250:
            pokemon.getStateMachine().changeState(FaintedState())
            
    def exit(self, pokemon):
        print "Leaving tired state"
        
    def getName(self):
        return "Tired"