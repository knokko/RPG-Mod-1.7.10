package knokko.rpg.worldgen.firelands;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import knokko.rpg.blocks.main.RPGBlocks;
import knokko.rpg.entity.creature.EntityFireEye;
import knokko.rpg.entity.monster.EntityFireDragon;
import knokko.rpg.worldgen.Hill;
import knokko.util.ChunkUtils;
import knokko.util.Position;
import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;

public class FireLandsChunkProvider implements IChunkProvider{
	
	public World world;
	
	public Random random = new Random();
	public NBTTagCompound heights = new NBTTagCompound();
	public NBTTagCompound biomes = new NBTTagCompound();
	
	public int biome;
	public List spawnList = new ArrayList();
	
	public FireLandsChunkProvider(World worldObj){
		world = worldObj;
		spawnList.add(new SpawnListEntry(EntityFireEye.class, 20, 3, 5));
		spawnList.add(new SpawnListEntry(EntityFireDragon.class, 5, 1, 1));
	}
	
	@Override
	public boolean chunkExists(int x, int z) {
		return true;
	}

	@Override
	public Chunk provideChunk(int x, int z) {
		Block[] blocks = new Block[32768];
		if(random.nextInt(1000) == 351){
			biome = random.nextInt(4);
		}
		biomes.setInteger("x:" + x + "z:" + z, biome);
		if(biome == 0){
			return generateFireLands(blocks, x, z);
		}
		else if(biome == 1){
			return generateHighLands(blocks, x, z);
		}
		else if(biome == 2){
			return generateOcean(blocks, x, z);
		}
		else {
			return generateLowLands(blocks, x, z);
		}
	}

	@Override
	public Chunk loadChunk(int x, int z) {
		return provideChunk(x, z);
	}

	@Override
	public void populate(IChunkProvider provider, int x, int z) {
	}

	@Override
	public boolean saveChunks(boolean b, IProgressUpdate update) {
		return true;
	}

	@Override
	public boolean unloadQueuedChunks() {
		return false;
	}

	@Override
	public boolean canSave() {
		return true;
	}

	@Override
	public String makeString() {
		return "FireLands";
	}

	@Override
	public List getPossibleCreatures(EnumCreatureType type, int x, int y, int z) {
		Chunk chunk = world.getChunkFromBlockCoords(x, z);
		if(!chunk.hasEntities){
			return spawnList;
		}
		else {
			return null;
		}
	}

	@Override
	public ChunkPosition func_147416_a(World world, String string, int x, int y, int z) {
		return null;
	}

	@Override
	public int getLoadedChunkCount() {
		return 0;
	}

	@Override
	public void recreateStructures(int x, int z) {}

	@Override
	public void saveExtraData() {}
	
	public Chunk generateFireLands(Block[] blocks, int x, int z){
		blocks = ChunkUtils.fillBlocks(blocks, 0, 51, 0, 15, 53, 15, Blocks.lava);
		blocks = ChunkUtils.fillBlocks(blocks, 0, 0, 0, 15, 50, 15, RPGBlocks.basalt);
		int times = 0;
		while(times < 10){
			blocks = ChunkUtils.makeOres(blocks, Blocks.glowstone, random.nextInt(15), random.nextInt(70), random.nextInt(15), random, Blocks.obsidian);
			++times;
		}
		times = 51;
		while(times < 65){
			blocks = makeHill(blocks, x, z, times, 20, RPGBlocks.basalt);
			++times;
		}
		blocks = ChunkUtils.spawnOres(blocks, RPGBlocks.ruby_ore, RPGBlocks.basalt, 2, 30, random);
		return new Chunk(world, blocks, x, z);
	}
	
	public Chunk generateHighLands(Block[] blocks, int x, int z){
		blocks = ChunkUtils.fillBlocks(blocks, 0, 0, 0, 15, 53, 15, RPGBlocks.basalt);
		int times = 54;
		while(times < 70){
			blocks = makeHill(blocks, x, z, times, 20, RPGBlocks.basalt);
			++times;
		}
		blocks = ChunkUtils.spawnOres(blocks, RPGBlocks.ruby_ore, RPGBlocks.basalt, 2, 30, random);
		return new Chunk(world, blocks, x, z);
	}
	
	public Chunk generateLowLands(Block[] blocks, int x, int z){
		blocks = ChunkUtils.fillBlocks(blocks, 0, 0, 0, 15, 48, 15, RPGBlocks.basalt);
		blocks = ChunkUtils.fillBlocks(blocks, 0, 49, 0, 15, 53, 15, Blocks.lava);
		int times = 49;
		while(times < 60){
			blocks = makeHill(blocks, x, z, times, 20, RPGBlocks.basalt);
			++times;
		}
		blocks = ChunkUtils.spawnOres(blocks, RPGBlocks.ruby_ore, RPGBlocks.basalt, 2, 30, random);
		return new Chunk(world, blocks, x, z);
	}
	
	public Chunk generateOcean(Block[] blocks, int x, int z){
		blocks = ChunkUtils.fillBlocks(blocks, 0, 0, 0, 15, 20, 15, RPGBlocks.basalt);
		blocks = ChunkUtils.fillBlocks(blocks, 0, 21, 0, 15, 53, 15, Blocks.lava);
		int times = 21;
		while(times < 30){
			blocks = makeHill(blocks, x, z, times, 20, RPGBlocks.basalt);
			++times;
		}
		blocks = ChunkUtils.spawnOres(blocks, RPGBlocks.ruby_ore, RPGBlocks.basalt, 2, 30, random);
		return new Chunk(world, blocks, x, z);
	}
	
	protected Block[] makeHill(Block[] blocks, int x, int z, int height, int chance, Block block){
		int x2 = 0;
		while(x2 < 16){
			int z2 = 0;
			while(z2 < 16){
				int n = heights.getInteger("x:" + (x * 16 + x2) + "z:" +  (z * 16 + z2 - 1));
				int s = heights.getInteger("x:" + (x * 16 + x2) + "z:" +  (z * 16 + z2 + 1));
				int w = heights.getInteger("x:" + (x * 16 + x2 - 1) + "z:" +  (z * 16 + z2));
				int e = heights.getInteger("x:" + (x * 16 + x2 + 1) + "z:" +  (z * 16 + z2));
				if(n == 0){
					n = height;
				}
				if(s == 0){
					s = height;
				}
				if(w == 0){
					w = height;
				}
				if(e == 0){
					e = height;
				}
				if(n >= height - 1 && s >= height - 1 && w >= height - 1 && e >= height - 1 && random.nextInt(chance) != 0){
					blocks = ChunkUtils.setBlock(blocks, x2, height, z2, block);
					heights.setInteger("x:" + (x * 16 + x2) + "z:" + (z * 16 + z2), height);
				}
				++z2;
			}
			++x2;
		}
		return blocks;
	}
}
