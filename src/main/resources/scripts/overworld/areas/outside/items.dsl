
// starting pokemon balls
def startPokemon = ["charmender", "squertle", "bulbasar"]

startPokemon.each{ int index, String name ->
    item 'pokeball', 10 + index, 10, {
		def wantsPokemon = decission("do you want to choose "+ name +" as your starting pokemon?") 
        if(wantsPokemon){
            selectPlayer{
                givePokemon{
					species=name
					statistics{
						health=10
						attack=1
						defence=1
						speed=2
					}
					experiance=0
				}
            }
        }
    }
}
item 'potion', 14, 12
item 'potion', 14, 13