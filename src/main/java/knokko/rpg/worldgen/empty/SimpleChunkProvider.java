package knokko.rpg.worldgen.empty;

import java.util.List;

import knokko.util.BuildUtils;
import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;

public class SimpleChunkProvider implements IChunkProvider {
	
	public World world;
	public Block[] basicChunk;
	
	public SimpleChunkProvider(World worldObj){
		world = worldObj;
		basicChunk = new Block[65536];
	}
	
	public SimpleChunkProvider(World worldObj, Block[] chunkBlueprint){
		world = worldObj;
		basicChunk = chunkBlueprint;
	}
	
	@Override
	public boolean chunkExists(int x, int z) {
		return true;
	}

	@Override
	public Chunk provideChunk(int x, int z) {
		return new Chunk(world, basicChunk, x, z);
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
		return "Simple Chunk Provider";
	}

	@Override
	public List getPossibleCreatures(EnumCreatureType creatureType, int x, int y, int z) {
		return null;
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
	public void recreateStructures(int x, int z) {
	}

	@Override
	public void saveExtraData() {
	}

}
