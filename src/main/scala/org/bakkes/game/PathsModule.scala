package org.bakkes.game

import java.nio.file.{FileSystems, Path}

import com.google.inject.Provides
import com.google.inject.name.{Named, Names}
import net.codingwell.scalaguice.ScalaModule
import org.bakkes.game.model.map.IAreaNameAcces

class PathsModule extends AModule with ScalaModule{

	override def configure {
		//val base = FileSystems.getDefault.getPath("build").resolve("resources").resolve("main") // production?
		//val base = FileSystems.getDefault.getPath("bin") //eclipse
		val base = FileSystems.getDefault.getPath("out").resolve("production").resolve("Slick2DRPG") // intelij
		bind[Path].toInstance(base)
		val scripts = bind(base, "scripts")
		bind(scripts, "pokemon", "scriptPokemon")
		bind(scripts, "moves")
		bind(scripts, "items", "scriptItems")
		val overworld = bind(scripts, "overworld")
		bind(overworld, "areas")
		bind(overworld, "animations")
		val sprites = bind(base, "sprites")
		bind(sprites, "pokemon", "spritePokemon")
		bind(sprites, "items", "spriteItems")
		bind(base, "map")
	}

	private def bind(base: Path, dirName: String): Path = bind(base, dirName, dirName)

	private def bind(base: Path, dirName: String, annoName: String): Path = {
		val result: Path = base.resolve(dirName)
		bind[Path].annotatedWith(Names.named(annoName)).toInstance(result)
		return result
	}

	@Provides
	@Named("current area") def getScriptFolder(named: IAreaNameAcces, @Named("areas") path: Path): Path = {
		return path.resolve(named.getAreaName)
	}
}