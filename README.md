# Slick2DRPG

This is a cheap pokemon knockoff, made because as a child I had many
frustrations with the original game. For example:

* Why can I only have 4 moves for each pokemon
* Why does every action get precided by a very slow moving text (fixed later by emulators)
* Why can I only carry 6 pokemon (probably because the gameboy had to little ram)
*	chris told me this is because they want you to succeed with only 6.
* The battles are kindof repetative, I think I already made them more fun
* To much is decided on level alone, (I might have made it worse)

## TODO
First I will try to emulate the orignal pokemongame. I'll create a working (stable) prototype
as soon as possible.
* catch pokemon
* Make the overworld work
	* Go into buildings
	* different areas which would contain
		* diferent wild pokemon for each area
		* reduce script sizes (the original pokemon had hunderds of trainers, imagine, it would be quite a bit less annoying to maintain this with trainers for each area (and more stable)
	* add a pokecenter
	* add a mall
	* add gyms
* add a story line
	* quest support
	* A horrible story line
	* probably a bunch of side quests

# DONE
* animation for npc's trough scripting.
* pathfinding was a real bitch
* declaring pokemon and their moves
* own pokemon receives xp after batle
* own pokemon levels up
* talk to npc's
* Replace fuzzy logic with RNG, this makes it easier to add moves, besides the AI, in the orignial pokemon was also retarded
* Replace python with groovy (better integration, while the scripting is very similiar)
* Implement a proper MVC pattern, these kind of games are made for that, besides the current 'entities (which are models I supose)' can now draw anything they want
* add trainers and npc's trough scripting
* add items to the overwolrd trough scripting
