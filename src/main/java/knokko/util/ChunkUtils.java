package knokko.util;

import java.util.Random;

import net.minecraft.block.Block;

public class ChunkUtils {
	
	public static Block[] setBlock(Block[] blocks, int x, int y, int z, Block block){
		if(x >= 0 && x <= 16 && y >= 0 && y <= 128 && z >= 0 && z <= 15){
			int i = y + (z * 128) + (x * 2048);
			try {
				blocks[i] = block;
			} catch(Throwable throwable){
				System.out.println("building block failed! i: " + i + " x: " + x + " y: " + y + " z: " + z);
			}
		}
		return blocks;
	}
	
	public static Block[] fillBlocks(Block[] blocks, int x1, int y1, int z1, int x2, int y2, int z2, Block block){
		int minX = Math.min(x1, x2);
		int maxX = Math.max(x2, x1);
		int minY = Math.min(y1, y2);
		int maxY = Math.max(y1, y2);
		int minZ = Math.min(z1, z2);
		int maxZ = Math.max(z1, z2);
		int x = minX;
		while(x <= maxX){
			int y = minY;
			while(y <= maxY){
				int z = minZ;
				while(z <= maxZ){
					blocks = setBlock(blocks, x, y, z, block);
					++z;
				}
				++y;
			}
			++x;
		}
		return blocks;
	}
	
	public static Block[] makeOres(Block[] chunk, Block ore, int x, int y, int z, Random random, Block old){
		try {
			Block block1 = getBlock(chunk, x, y, z);
			Block block2 = getBlock(chunk, x, y + 1, z);
			Block block3 = getBlock(chunk, x, y, z + 1);
			Block block4 = getBlock(chunk, x, y + 1, z + 1);
			Block block5 = getBlock(chunk, x + 1, y, z);
			Block block6 = getBlock(chunk, x + 1, y + 1, z);
			Block block7 = getBlock(chunk, x + 1, y, z + 1);
			Block block8 = getBlock(chunk, x + 1, y + 1, z + 1);
			if(block1 == old){
				chunk = setBlock(chunk, x, y, z, ore);
			}
			if(block2 == old){
				chunk = setBlock(chunk, x, y + 1, z, ore);
			}
			if(block3 == old){
				chunk = setBlock(chunk, x, y, z + 1, ore);
			}
			if(block4 == old){
				chunk = setBlock(chunk, x, y + 1, z + 1, ore);
			}
			if(block5 == old){
				chunk = setBlock(chunk, x + 1, y, z, ore);
			}
			if(block6 == old){
				chunk = setBlock(chunk, x + 1, y + 1, z, ore);
			}
			if(block7 == old){
				chunk = setBlock(chunk, x + 1, y, z + 1, ore);
			}
			if(block8 == old){
				chunk = setBlock(chunk, x + 1, y + 1, z + 1, ore);
			}
		} catch(Throwable throwable){}
		return chunk;
	}
	
	public static Block getBlock(Block[] chunk, int x, int y, int z){
		return chunk[y + (z * 128) + (x * 2048)];
	}
	
	public static Block[] makeHill(Block[] chunk, int maxHeight, int lenght, Block block, int x, int y, int z){
		double y2 = y + maxHeight;
		int x2 = x - lenght;
		while(x2 <= x + lenght){
			int z2 = z - lenght;
			while(z2 <= z + lenght){
				double distance = Position.getSquaredDistance(new Position(x2, 1, z2), new Position(x, 1, z));
				chunk = fillBlocks(chunk, x2, y, z2, x2, ExtraUtils.fromDouble(y + maxHeight - (distance * 0.25)), z2, block);
				++z2;
			}
			++x2;
		}
		return chunk;
	}
	
	public static Block[] spawnOres(Block[] chunk, Block ore, Block old, int ammount, int maxHeight, Random random){
		int times = 0;
		while(times <= ammount){
			chunk = makeOres(chunk, ore, random.nextInt(16), random.nextInt(maxHeight) + 1, random.nextInt(15), random, old);
			++times;
		}
		return chunk;
		
	}
}
