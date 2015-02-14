package org.bakkes.game;

import java.nio.file.FileSystems;
import java.nio.file.Path;

import org.bakkes.game.model.map.IAreaNameAcces;

import com.google.inject.Provides;
import com.google.inject.name.Named;
import com.google.inject.name.Names;

public class PathsModule extends AModule {
	@Override
	public void configure(){
		final Path base = FileSystems.getDefault().getPath("bin");
		bind(Path.class).toInstance(base);
        final Path scripts = bind(base, "scripts");
        bind(scripts, "pokemon","scriptPokemon");
        bind(scripts, "moves");
        bind(scripts, "items", "scriptItems");
        final Path overworld = bind(scripts, "overworld");
        bind(overworld, "areas");
        bind(overworld, "animations");
        final Path sprites = bind(base, "sprites");
        bind(sprites, "pokemon", "spritePokemon");
        bind(sprites, "items", "spriteItems");
        bind(base, "map");
	}
	private Path bind(final Path base, final String dirName){
		return bind(base,dirName,dirName);
	}
	private Path bind(final Path base, final String dirName, final String annoName){
        final Path result = base.resolve(dirName);
        bind(Path.class).annotatedWith(Names.named(annoName)).toInstance(result);
        return result;
	}
	@Provides
	@Named("current area")
	public Path getScriptFolder(final IAreaNameAcces named, @Named("areas") final Path path){
		return path.resolve(named.getAreaName());
	}
}
