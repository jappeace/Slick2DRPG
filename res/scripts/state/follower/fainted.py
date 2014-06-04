from org.bakkes.game.entity.follower.state import IState

class FaintedState(IState):
    def enter(self, pokemon):
        pokemon.getParent().showDialog("Your pokemon has fainted, you can only get it back when you get\nsome protein from the nice old lady!")
        pokemon.isHealthy = False
        
    def execute(self, pokemon):
        inv = pokemon.getParent().getInventory()
        if inv.hasItem(4): #has item no. 4, protein
            inv.deleteItem(inv.getSlot(4)) #remove protein & can walk again
            pokemon.stepsTaken = 0
            pokemon.isHealthy = True
            pokemon.getParent().showDialog("Your pokemon ate the protein and can walk normally again!")
            pokemon.getStateMachine().changeState(FollowerState())
            
    def exit(self, pokemon):
        print "Leaving Fainted state"
        
    def getName(self):
        return "Fainted"