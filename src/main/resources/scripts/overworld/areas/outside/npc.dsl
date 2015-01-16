

person {
	name 'nice old lady'
	facing 'south'
	location 12,7
	onInteract{
		give 4,4
		dialog "Here's some protein, your pokemon will love it!" 
		tought "wtf is protein"
		dialog "You just talked to NPC 1!\nWelcome to this game."
		tought "This is why everyone thinks you're crazy"
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