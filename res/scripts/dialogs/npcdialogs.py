from org.bakkes.game.math import Vector2

def talkto_1(game):
	game.showDialog("You just talked to NPC 1!\nWelcome to this game.\nInstantly updated?")
	
def talkto_2(game):
	game.showDialog("Have a pokeball and some eggs")
	game.getPlayer().getInventory().addItem(0)
	for i in range(0, 3, 1):
		game.getPlayer().getInventory().addItem(2)
		
def talkto_3(game):
	game.showDialog("What are you doing here?");
	
def talkto_4(game):
	game.showDialog("Sorry, I can't let you in here!")
	game.showDialog("I'll just send you back.")
	game.getPlayer().moveTo(Vector2(8, 8))
	game.showDialog("bye")