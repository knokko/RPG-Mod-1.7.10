package knokko.rpg.entity.projectile;

import knokko.rpg.effect.EnergyBall;
import knokko.rpg.entity.monster.EntityEmpire;
import knokko.util.Line;
import knokko.util.Position;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityExplosiveSpell extends EntityThrowable{
	public float power = 3;
	public Position start;
	
	public EntityExplosiveSpell(World world) {
		super(world);
	}
	public EntityExplosiveSpell(World world, EntityLivingBase entity, float power){
		super(world, entity);
		this.power = power * 2;
	}

	@Override
	public void onImpact(MovingObjectPosition mop) {
		if(!(mop.entityHit instanceof EntityEmpire && getThrower() instanceof EntityEmpire)){
			EnergyBall.makeBall(worldObj, mop.hitVec.xCoord, mop.hitVec.yCoord, mop.hitVec.zCoord, power, 15, 0.1, 0.1, 5000);
			boolean flag;
			if(getThrower() instanceof EntityPlayer)
				flag = worldObj.getGameRules().getGameRuleBooleanValue("rpgExplosions");
			else 
				flag = worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing");
			if(!worldObj.isRemote){
				worldObj.createExplosion(getThrower(), mop.hitVec.xCoord, mop.hitVec.yCoord, mop.hitVec.zCoord, power, flag);
			}
			setDead();
		}
	}
	
	public void onUpdate(){
		super.onUpdate();
		if(ticksExisted > 400){
			setDead();
		}
		if(start != null){
			Line line = new Line(new Position(this), start);
			line.spawnParticles(worldObj, 1, 0, 0, 0.1, 5);
		}
		start = new Position(this);
		motionX *= 1.02;
		motionY *= 1.02;
		motionZ *= 1.02;
	}
	
	public float getGravityVelocity(){
		return 0;
	}
}
