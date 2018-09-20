package knokko.util;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class BlockUtils {
	public static void placeBlockInAir(World world, int x, int y, int z, Block block){
		if(world.isAirBlock(x, y, z)){
			world.setBlock(x, y, z, block);
		}
	}
	public static int getHighestBlockHeight(int x, int z, World world, Block block){
		int y = 256;
		while(world.getBlock(x, y, z) == block){
			return y;
		}
		while(y >= 0 && world.getBlock(x, y, z) != block){
			--y;
		}
		return y;
	}
	public static int getLowestBlockHeight(int x, int z, World world, Block block){
		int y = 0;
		while(world.getBlock(x, y, z) == block){
			return y;
		}
		while(y <= 256 && world.getBlock(x, y, z) != block){
			++y;
		}
		return y;
	}
	public static int getHighestBlock(World world, int x, int z){
		int y = 260;
		while(y >= 0){
			if(world.getBlock(x, y, z).getBlockHardness(world, x, y, z) == 0){
				--y;
			}
			else {
				return y;
			}
		}
		return y;
	}
	public static boolean isPositionSafeForEnderman(World world, int x, int y, int z){
		Block block = world.getBlock(x, y, z);
		Block block2 = world.getBlock(x, y, z);
		if(block.getBlockHardness(world, x, y, z) == 0 && block2.getBlockHardness(world, x, y, z) == 0){
			if(!(block2 instanceof BlockLiquid) && !(block instanceof BlockLiquid)){
				if(world.isRaining()){
					if(getHighestBlock(world, x, z) > y){
						return true;
					}
					else {
						return false;
					}
				}
				else {
					return true;
				}
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
	public static int getHighestSafePointForEnderman(World world, int x, int z){
		int y = 260;
		while(y >= 0){
			Block block = world.getBlock(x, y, z);
			if(block.getBlockHardness(world, x, y, z) == 0){
				--y;
			}
			else {
				if(isPositionSafeForEnderman(world, x, y + 1, z)){
					return y;
				}
				else {
					--y;
				}
			}
		}
		return y;
	}
	
	public static Block randomBlock(Random random, Block... block){
		return block[random.nextInt(block.length)];
	}
}
