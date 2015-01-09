from org.bakkes.game import GameInfo
from org.newdawn.slick.state.transition import FadeOutTransition, FadeInTransition
def talkto_1(game):
	game.showDialog("You just talked to NPC 1!\nWelcome to this game.")
	game.getPlayer().getInventory().addItem(4)
	game.getPlayer().getInventory().addItem(4)
	game.showDialog("Here's some protein, your pokemon will love it!")
	
def talkto_2(game):
	game.showDialog("I killed all the birds, I'm sorry. I was hungry"+
                " and wanted to eat chips")
		
def talkto_3(game):
	game.showDialog("What are you doing here?");
	
def talkto_4(game):
	game.showDialog("Sorry, I can't let you in here!")
	game.showDialog("I'll just send you back.")
	game.getPlayer().moveTo(8, 8)
	game.showDialog("bye")
	
