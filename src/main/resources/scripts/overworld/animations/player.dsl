package scripts.overworld.animations;
filename = "player.png"
duration = 200

(0..3).each{ int direction ->
	// i did not just change the values of direction to coincide with this sprite sheet
	// maybe i did :*(
	// if this is weird to you use north{ } east { } south { } west { } instead
	buildDirection direction, {
        (0..2).each{
            tile it,direction
        }
	}
}