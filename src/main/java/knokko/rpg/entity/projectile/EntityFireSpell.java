package knokko.rpg.entity.projectile;

import knokko.util.ExtraUtils;
import knokko.util.Line;
import knokko.util.Position;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityFireSpell extends EntityThrowable{
	
	public float power;
	public Position start;

	public EntityFireSpell(World world, EntityLivingBase entity, float power) {
		super(world, entity);
		this.power = power;
		start = new Position(this);
	}
	
	public EntityFireSpell(World world){
		super(world);
		power = 3;
		start = new Position(this);
	}

	@Override
	public void onImpact(MovingObjectPosition mop) {
		Entity entity = mop.entityHit;
		Position position = new Position(this);
		int range = ExtraUtils.fromDouble(power * 0.2);
		if(entity != getThrower() && entity instanceof EntityLivingBase && !entity.isImmuneToFire() ){
			if(!((EntityLivingBase) entity).isPotionActive(12)){
				DamageSource source = new EntityDamageSourceIndirect("fire spell", this, getThrower()).setFireDamage();
				entity.attackEntityFrom(source, power);
				entity.setFire(range * 5);
			}
		}
		int x = -range;
		while(x <= range){
			int y = -range;
			while(y <= range){
				int z = -range;
				while(z <= range){
					Block block = worldObj.getBlock(ExtraUtils.fromDouble(posX) + x, ExtraUtils.fromDouble(posY) + y, ExtraUtils.fromDouble(posZ) + z);
					Position pos = new Position(posX + x, posY + y, posZ + z);
					if(block.getBlockHardness(worldObj, ExtraUtils.fromDouble(posX) + x, ExtraUtils.fromDouble(posY) + y, ExtraUtils.fromDouble(posZ) + z) == 0 && pos.getSquaredDistance(position) <= range){
						worldObj.setBlock(ExtraUtils.fromDouble(posX) + x, ExtraUtils.fromDouble(posY) + y, ExtraUtils.fromDouble(posZ) + z, Blocks.fire);
					}
					++z;
				}
				++y;
			}
			++x;
		}
		setDead();
	}
	
	public float getGravityVelocity(){
		return 0;
	}
	public void onUpdate(){
		super.onUpdate();if(start != null){
			Line line = new Line(new Position(this), start);
			line.spawnParticles(worldObj, 1, 0.67, 0.33, 0.1, 5);
		}
		start = new Position(this);
		if(ticksExisted > 1000){
			setDead();
		}
		motionX *= 1.02;
		motionY *= 1.02;
		motionZ *= 1.02;
	}
	
	public void writeEntityToNBT(NBTTagCompound nbt){
		nbt.setFloat("power", power);
		super.writeEntityToNBT(nbt);
	}
	public void readEntityFromNBT(NBTTagCompound nbt){
		power = nbt.getFloat("power");
		super.readEntityFromNBT(nbt);
	}
}
