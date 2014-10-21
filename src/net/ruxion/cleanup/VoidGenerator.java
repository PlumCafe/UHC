package net.ruxion.cleanup;

import java.util.Random;

import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;

public class VoidGenerator extends ChunkGenerator
{
	public VoidGenerator(){
		
	}
	
	public byte[][] generateBlockSections(World world, Random random, int chunkX, int chunkZ, BiomeGrid biomeGrid)
	{
		return new byte[world.getMaxHeight() / 16][];
	}
}