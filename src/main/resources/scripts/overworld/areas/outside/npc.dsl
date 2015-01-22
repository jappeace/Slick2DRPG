person {
	name 'nice old lady'
	facing 'south'
	location 12,7
	onInteract{
		selectPlayer{
            giveItem "potion","potion"
		}
		dialog "Here are some potions, your pokemon will love it!" 
		tought "uhhh, I wish there was just a poke center"
		dialog "You just talked to NPC 1!\nWelcome to this game."
		tought "This is why everyone thinks you're crazy, calling yourself npc 1?"
	}
}
person {
	name 'friend'
	facing 'east'
	onInteract{
		dialog "I hate you!"
		tought "there is no ability for me to speak back, so ... .. ."
	}
	location 20,5
}
person {
	name 'bencent'
	facing 'west'
	onInteract{
		dialog "Vanaaf waterpijp?" 
		tought "kzit in china man"
	}
	location 8,14
}
person {
	name 'SLEG'
	facing 'north'
	location 20,30
	onInteract{
		dialog "I am SLEG, son of SLEG"
		dialog "Give me all your money or there will be battle" 
		dialog "... functionality not implemented yet..." 
		tought "WTF!"
	}
}
person {
	name 'bencent'
	facing 'south'
	onInteract{
		dialog "I don't want this pokemon any more, do you want it?"
		tought "uhhh, I hate pokemon"
		player{
			givePokemon{
				species="charmender"
				xp=500
				statistics{
					health=50
					speed=10
					defence=10
					attack=10
				}
			}
		}
	}
	location 50,30
}