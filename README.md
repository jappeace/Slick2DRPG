# Slick2DRPG

This is a cheap Pokemon knockoff, made because as a child I had many
frustrations with the original game. For example:

* Why can I only have 4 moves for each Pokemon
* Why does every action get preceded by a very slow moving text (fixed later by emulators)
* Why can I only carry 6 Pokemon (probably because the Gameboy had to little ram)
*	Chris told me this is because they want you to succeed with only 6.
* The battles are kind of repetitive, I think I already made them more fun
* To much is decided on level alone, (I might have made it worse)

## TODO
First I will try to emulate the original Pokemon game. I'll create a working (stable) prototype
as soon as possible.
* Catch Pokemon
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
* see Pokemon in the menu with all their glory (600\*600 pixels)
* animation for NPC's trough scripting.
* path finding was a real bitch
* declaring Pokemon and their moves
* own Pokemon receives xp after batle
* own Pokemon levels up
* talk to NPC's
* Replace fuzzy logic with RNG, this makes it easier to add moves, besides the AI, in the orignial pokemon was also retarded
* Replace python with groovy (better integration, while the scripting is very similiar)
* Implement a proper MVC pattern, these kind of games are made for that, besides the current 'entities (which are models I supose)' can now draw anything they want
* add trainers and NPC's trough scripting
* add items to the over world trough scripting
