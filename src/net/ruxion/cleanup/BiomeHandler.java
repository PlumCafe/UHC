package net.ruxion.cleanup;

import java.lang.reflect.Field;
import java.util.Arrays;

import net.minecraft.server.v1_7_R4.BiomeBase;
import net.ruxion.cleanup.Cleanup.Biome;

import org.bukkit.Bukkit;

public class BiomeHandler
{
	private BiomeBase[] origBiomes;

	public BiomeHandler() {
		this.origBiomes = getMcBiomesCopy();
	}

	public void swapBiome(Biome oldBiome, Biome newBiome) {
		if (oldBiome.getId() != Biome.SKY.getId()) {
			BiomeBase[] biomes = getMcBiomes();
			biomes[oldBiome.getId()] = getOrigBiome(newBiome.getId());
		} else {
			Bukkit.getLogger().warning("Cannot swap SKY biome!");
		}
	}

	private BiomeBase[] getMcBiomesCopy() {
		BiomeBase[] b = getMcBiomes();
		return (BiomeBase[]) Arrays.copyOf(b, b.length);
	}

	private BiomeBase[] getMcBiomes() {
		try {
			Field biomeF = BiomeBase.class.getDeclaredField("biomes");
			biomeF.setAccessible(true);
			return (BiomeBase[]) biomeF.get(null);
		} catch (IllegalAccessException | NoSuchFieldException e) {
			e.printStackTrace();
		}
		return new BiomeBase[256];
	}

	private BiomeBase getOrigBiome(int value) {
		return this.origBiomes[value];
	}

}