package org.bakkes.game.model.entity;

import org.bakkes.game.AModule;
import org.bakkes.game.R;
import org.bakkes.game.model.map.IAreaNameAcces;

/**
 * some modules change their provided result based on the area's
 * if so extend this class
 *
 * these modules will often read scripts and return the object that
 * are configured by the scripts
 *
 * this class provides some commen util functions
 */
public abstract class AAreaModule extends AModule{


	private String areaName = "";
	protected boolean isNewArea(final IAreaNameAcces areaNameHolder){
		if(areaName.equals(areaNameHolder.getAreaName())){
			return false;
		}
		areaName = areaNameHolder.getAreaName();
		return true;
	}

	protected String getScriptFolder(){
		return R.overworldAreas + areaName + "/";
	}
}
