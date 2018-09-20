package knokko.rpg.entity.effect;

import java.util.Random;

import knokko.util.BlockUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityReddustFX;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class EntityBlood extends Entity{
	public int lifeTime;
	public int livingTicks;
	public EntityBlood(World world) {
		super(world);
	}
	public EntityBlood(World world, double x, double y, double z, int lifetime){
		super(world);
		setPosition(x, y, z);
		lifeTime = lifetime;
	}
	@Override
	protected void entityInit() {
	}
	@Override
	protected void readEntityFromNBT(NBTTagCompound nbt) {
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbt) {
	}
	public void onUpdate(){
		++livingTicks;
		if(livingTicks >= lifeTime){
			setDead();
		}
		if(worldObj.isRemote){
			blood(posX, posY, posZ);
		}
	}
	public void blood(double x, double y, double z) {
		Random random = new Random();
		int times = 0;
		while(times < 30){
			EntityReddustFX fx = new EntityReddustFX(worldObj, (x - 1) + rand.nextDouble() * 2, (y - 1) + rand.nextDouble() * 2, (z - 1) + rand.nextDouble() * 2, 1, 1, 1);
			fx.setRBGColorF(1, 0, 0);
			fx.renderDistanceWeight = 1;
			fx.motionX = 0;
			fx.motionY = -0.1;
			fx.motionZ = 0;
			Minecraft.getMinecraft().effectRenderer.addEffect(fx);
			++times;
		}
	}
	public void onEntityUpdate(){}
}
