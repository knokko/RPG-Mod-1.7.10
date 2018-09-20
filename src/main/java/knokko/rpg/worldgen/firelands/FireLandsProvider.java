package knokko.rpg.worldgen.firelands;

import java.util.Random;

import knokko.rpg.entity.creature.EntityFireEye;
import knokko.rpg.entity.monster.EntityFireDragon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;

public class FireLandsProvider extends WorldProvider{

	@Override
	public String getDimensionName() {
		return "Fire Lands";
	}
	
	public IChunkProvider createChunkGenerator(){
		return new FireLandsChunkProvider(worldObj);
	}
	
	@SideOnly(Side.CLIENT)
	public Vec3 getSkyColor(Entity entity, float f){
		return Vec3.createVectorHelper(1, 0, 0);
	}
	
	 @SideOnly(Side.CLIENT)
	 public Vec3 getFogColor(float f1, float f2){
	    return Vec3.createVectorHelper(1, 0, 0);
	 }
	 
	 @SideOnly(Side.CLIENT)
	 public boolean doesXZShowFog(int p_76568_1_, int p_76568_2_){
	    return true;
	 }
	 
	 @SideOnly(Side.CLIENT)
	 public Vec3 drawClouds(float partticks){
		 return Vec3.createVectorHelper(0, 0, 0);
	 }
	 
	 public long getWorldTime(){
		 return 6000;
	 }
	 
	 public boolean canDoRainSnowIce(Chunk chunk){
		 return false;
	 }
	 
	 public static Entity getRandomSpawnAbleEntity(World world, Random random){
		 int r = random.nextInt(100);
		 if(r == 3){
			 return new EntityFireDragon(world);
		 }
		 else {
			 return new EntityFireEye(world);
		 }
	 }
}
