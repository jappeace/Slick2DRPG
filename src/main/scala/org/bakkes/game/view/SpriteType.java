package org.bakkes.game.view;

import org.bakkes.game.model.pokemon.Pokemon;

public enum SpriteType {
	big,
	small,
	front,
	back;
	public String getImageName(final Pokemon target){
		String shiny = "";
		if(target.isShiny()){
			shiny = "shiny_";
		}
		String female = "";
		if(target.isFemale()){
			female = "_female";
		}
		return target.getSpriteName() + "/" + shiny + this.name() + female +".png";
	}
}
