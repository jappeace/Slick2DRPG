
// starting pokemon balls
def startPokemon = ["charmender", "squertle", "bulbasar"]

int index = 0
startPokemon.each{  String name ->
    item 'pokeball', 10 + index, 10, {
		def wantsPokemon = decision("do you want to choose "+ name +" as your starting pokemon?") 
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
	index += 2
}
item 'potion', 16, 12
item 'potion', 16, 15