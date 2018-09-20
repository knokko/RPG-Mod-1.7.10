package knokko.rpg.worldgen;

import java.util.Random;

import knokko.rpg.blocks.main.RPGBlocks;
import knokko.rpg.main.KnokkoRPG;
import knokko.util.BlockUtils;
import knokko.util.BuildUtils;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;

public class RPGGenerator implements IWorldGenerator{

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		int randomX = chunkX * 16 + random.nextInt(16);
		int randomZ = chunkZ * 16 + random.nextInt(16);
		int y = BlockUtils.getHighestBlock(world, randomX, randomZ);
		int randomizer = random.nextInt(400);
		if(randomizer == 50 && y >= 0 && world.provider.dimensionId == 0){
			RPGBuildings.makeHouse(world, randomX, y, randomZ, randomX + random.nextInt(48) + 16, y + random.nextInt(10) + 10, randomZ + random.nextInt(20) + 10, Blocks.brick_block, Blocks.planks);
		}
		if(world.provider.dimensionId == KnokkoRPG.fireLandsId){
			if(random.nextInt(300) == 17){
				BuildUtils.makeVulcano(world, RPGBlocks.basalt, random, randomX, 51, randomZ);
			}
		}
	}
}
