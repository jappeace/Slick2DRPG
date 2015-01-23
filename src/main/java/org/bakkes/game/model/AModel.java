package org.bakkes.game.model;


public class AModel implements IModel{

	private String name;

	public AModel(){
		name = "anonymos";
	}


	@Override
	public String getName() {
		return name;
	}

	public void setName(final String name) {
		assert name != null;
		this.name = name;
	}
}
