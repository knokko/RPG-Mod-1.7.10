package knokko.util.bag;

import net.minecraft.block.Block;

public class BlockBag {
	public Block block;
	
	public int x;
	public int y;
	public int z;
	
	public double xd;
	public double yd;
	public double zd;
	
	public BlockBag(Block block, double x, double y, double z){
		this.block = block;
		this.x = (int) x;
		this.y = (int) y;
		this.z = (int) z;
		xd = x;
		yd = y;
		zd = z;
	}
	public String toString(){
		return "block: " + block + " x: " + x + " y: " + y + " z: " + z;
	}
}
