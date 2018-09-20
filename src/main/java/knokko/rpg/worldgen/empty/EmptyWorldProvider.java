package knokko.rpg.worldgen.empty;

import java.util.Random;

import knokko.util.BlockUtils;
import knokko.util.ChunkUtils;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.Vec3;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.client.IRenderHandler;

public class EmptyWorldProvider extends WorldProvider {

	@Override
	public String getDimensionName() {
		return "Empty World";
	}
	public boolean canRespawnHere(){
		return false;
	}
	public IChunkProvider createChunkGenerator(){
		Block[] blocks = new Block[32768];
		return new SimpleChunkProvider(worldObj, blocks);
	}
	public void registerWorldChunkManager(){
		super.registerWorldChunkManager();
		hasNoSky = true;
	}
	public long getWorldTime(){
		return 18000;
	}
	public boolean isSurfaceWorld(){
		return false;
	}
}
