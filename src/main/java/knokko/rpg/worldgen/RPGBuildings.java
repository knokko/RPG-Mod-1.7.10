package knokko.rpg.worldgen;

import java.util.Random;

import knokko.rpg.blocks.main.RPGBlocks;
import knokko.util.BuildUtils;
import knokko.util.ExtraUtils;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class RPGBuildings {
	
	public static void makeHouse(World world, int x1, int y1, int z1, int x2, int y2, int z2, Block block1, Block block2){
		Random random = new Random();
		int glassLengtX = ExtraUtils.fromDouble((x2 - x1) * 0.1);
		int glassPosX = ExtraUtils.fromDouble(x1 + ((x2 - x1) * 0.25));
		int glassPosX2 = x2 - ExtraUtils.fromDouble(((x2 - x1) * 0.25)) - glassLengtX;
		int glassPosY = ExtraUtils.fromDouble(y1 + ((y2 - y1) * 0.2));
		int height = y2 - y1;
		int cubeHeight = (int) (height * 0.6);
		int glassHeight = ExtraUtils.fromDouble(cubeHeight * 0.2);
		int doorLenght = ExtraUtils.fromDouble((x2 - x1) * 0.1);
		int doorPosX = ExtraUtils.fromDouble(x1 + ((x2 - x1) * 0.5) - (doorLenght * 0.5));
		int doorHeight = ExtraUtils.fromDouble(cubeHeight * 0.25);
		int tables = ExtraUtils.fromDouble(((x2 - x1) * (z2 - z1)) * 0.01);
		BuildUtils.makeHollowBox(world, x1, y1, z1, x2, y1 + cubeHeight, z2, block1);
		BuildUtils.fillBox(world, x1 + 1, y1 + 1, z1 + 1, x2 - 1, y1 + cubeHeight - 1, z2 - 1, Blocks.air);
		BuildUtils.makeFilledPyramide(world, x1, y1 + cubeHeight + 1, z1, x2, z2, block2);
		BuildUtils.makeXWall(world, glassPosX, glassPosY, z1, glassPosX + glassLengtX, glassPosY + glassHeight, Blocks.glass);
		BuildUtils.makeXWall(world, glassPosX2, glassPosY, z1, glassPosX2 + glassLengtX, glassPosY + glassHeight, Blocks.glass);
		BuildUtils.makeXWall(world, doorPosX, y1 + 1, z1, doorPosX + doorLenght, y1 + doorHeight, Blocks.air);
		while(tables > 0){
			int xLenght = 2 + random.nextInt(ExtraUtils.fromDouble((x2 - x1) * 0.1));
			int zLenght = ExtraUtils.fromDouble(xLenght * random.nextDouble());
			int tableX = x1 + 2 + random.nextInt(x2 - x1 - 5);
			int tableZ = z1 + 2 + random.nextInt(z2 - z1 - 4);
			BuildUtils.makeFloor(world, tableX, y1 + 1, tableZ, tableX + xLenght, tableZ + zLenght, RPGBlocks.table);
			--tables;
		}
	}
}
