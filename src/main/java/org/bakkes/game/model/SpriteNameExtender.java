package org.bakkes.game.model;

/**
 * pokemon have depending on the situation big, small, front or back images (not to speak of the shiny ones)
 * this class allows the views to pick the right ones
 */
public class SpriteNameExtender implements IHasSpriteName{

	private IHasSpriteName target;
	private String extension = "";
	public void setHasSpriteName(final IHasSpriteName spriteName){
		target = spriteName;
	}
	public void setExtension(final String to){
		extension = to;
	}
	@Override
	public String getName() {
		return target.getName();
	}

	@Override
	public void setName(final String to) {
		target.setName(to);
	}

	@Override
	public String getSpriteName() {
		return target.getSpriteName() + extension;
	}

	@Override
	public void setSpriteName(final String to) {
		target.setSpriteName(to);
	}

}
