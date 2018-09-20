package knokko.rpg.worldgen;

import java.util.Random;

import knokko.util.ExtraUtils;
import knokko.util.Position;

public class Hill {
	
	public Position base;
	
	public int southLenght;
	public int westLenght;
	public int eastLenght;
	public int northLenght;
	
	public int height;
	
	public Hill(Position base, int southLenght, int westLenght, int eastLenght, int northLenght, int maxHeight){
		this.base = base;
		this.southLenght = southLenght;
		this.westLenght = westLenght;
		this.eastLenght = eastLenght;
		this.northLenght = northLenght;
		this.height = maxHeight;
	}
	
	public static Hill randomHill(Position base, Random random, int maxLenght, int maxHeight){
		return new Hill(base, random.nextInt(maxLenght), random.nextInt(maxLenght), random.nextInt(maxLenght), random.nextInt(maxLenght), random.nextInt(maxHeight));
	}
	
	public boolean isInHill(int x, int z){
		boolean flag1 = false;
		boolean flag2 = false;
		if(ExtraUtils.isBetween(x, base.x, base.x - westLenght)){
			flag1 = true;
		}
		else if(ExtraUtils.isBetween(x, base.x, base.x + eastLenght)){
			flag1 = true;
		}
		if(ExtraUtils.isBetween(z, base.z, base.z - northLenght)){
			flag2 = true;
		}
		else if(ExtraUtils.isBetween(z, base.z, base.z + southLenght)){
			flag2 = true;
		}
		return flag1 && flag2;
	}
}
