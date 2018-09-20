package knokko.rpg.blocks;

import java.util.Random;

import knokko.rpg.main.KnokkoRPG;
import knokko.rpg.main.s;
import knokko.rpg.worldgen.empty.EmptyTeleporter;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class VoidPortal extends Block{

	public VoidPortal() {
		super(Material.portal);
		setCreativeTab(CreativeTabs.tabDecorations);
		setBlockName("voidportal");
		setBlockTextureName(s.t + "voidportal");
		setBlockUnbreakable();
		setResistance(18000000F);
		setLightLevel(0.5F);
	}
	
	public boolean isBlockNormalCube(){
		return false;
	}
	
	public boolean isBlockSolid(IBlockAccess acces, int x, int y, int z, int meta){
		return false;
	}
	
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity){
		if(entity.timeUntilPortal <= 0){
			if(entity.dimension == KnokkoRPG.voidWorldId){
				entity.travelToDimension(0);
			}
			else {
				entity.travelToDimension(KnokkoRPG.voidWorldId);
			}
		}
		entity.timeUntilPortal = 100;
	}
	
	public void onEntityColldedWithBlock(World world, int x, int y, int z, Entity entity1){
		 if (!entity1.worldObj.isRemote && !entity1.isDead)
	        {
	            world.theProfiler.startSection("changeDimension");
	            MinecraftServer minecraftserver = MinecraftServer.getServer();
	            int j = entity1.dimension;
	            int i = KnokkoRPG.voidWorldId;
	            if(i == j){
	            	i = 0;
	            }
	            WorldServer worldserver = minecraftserver.worldServerForDimension(j);
	            WorldServer worldserver1 = minecraftserver.worldServerForDimension(i);
	            entity1.dimension = i;
	            world.removeEntity(entity1);
	            entity1.isDead = false;
	            world.theProfiler.startSection("reposition");
	            minecraftserver.getConfigurationManager().transferEntityToWorld(entity1, j, worldserver, worldserver1, new EmptyTeleporter(worldserver));
	            world.theProfiler.endStartSection("reloading");
	            Entity entity = EntityList.createEntityByName(EntityList.getEntityString(entity1), worldserver1);

	            if (entity != null)
	            {
	                entity.copyDataFrom(entity1, true);
	                worldserver1.spawnEntityInWorld(entity);
	            }

	            entity1.isDead = true;
	            world.theProfiler.endSection();
	            worldserver.resetUpdateEntityTick();
	            worldserver1.resetUpdateEntityTick();
	            world.theProfiler.endSection();
	        }
	}
	
	public boolean isCollidable(){
		return true;
	}
	
	public boolean isNormalCube(){
		return false;
	}
	
	public boolean isOpaqueCube(){
		return false;
	}
	
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
    {
        return null;
    }
	
	public void randomDisplayTick(World world, int x, int y, int z, Random random){
		int times = 0;
		while(times < 50){
			world.spawnParticle("portal", x - 1 + 2 * random.nextDouble(), y - 1 + 2 * random.nextDouble(), z - 1 + 2 * random.nextDouble(), 0.1, 0.1, 0.1);
			++times;
		}
	}
}
