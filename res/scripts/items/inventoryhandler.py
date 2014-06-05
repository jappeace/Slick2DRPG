#from org.bakkes.game.items import *

def inventorySelected(slot, item, game):
    print item.getItemID()
    if item.getItemID() == 4: #protein
        game.getPlayer().getPokemon().initialize()
        game.getPlayer().getPokemon().info()
        game.getPlayer().getFollower().stepsTaken = 0
        game.getPlayer().getFollower().isHealthy = True
        game.getPlayer().getInventory().deleteItem(slot)
        game.showDialog("Your pokemon is now fully healed")
        game.checkDialogs()
        print "done"
#	if item.getItemID() == 0: #pokeball
#		print "Selected the pokeball"
#	elif item.getItemID() == 2: #egg
#		print "Selected the egg"