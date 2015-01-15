

person {
	onInteract{
		dialog "You just talked to NPC 1!\nWelcome to this game."
		give 4,4
		dialog "Here's some protein, your pokemon will love it!" 
	}
	facing 'south'
	name 'nice old lady'
	location 12,7
}
person {
	facing 'east'
	name 'friend'
	onInteract{
		dialog "I hate you!"
	}
	location 20,5
}
person {
	facing 'west'
	name 'bencent'
	onInteract{
		dialog "Vanaaf waterpijp?" 
	}
	location 8,14
}
person {
	onInteract{
		dialog "I am SLEG, son of SLEG"
		dialog "Give me all your money or there will be battle" 
		dialog "... functionality not implemented yet..." 
	}
	facing 'north'
	name 'SLEG'
	location 20,30
}