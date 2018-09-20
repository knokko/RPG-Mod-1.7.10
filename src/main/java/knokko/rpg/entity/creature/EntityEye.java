package knokko.rpg.entity.creature;

import knokko.rpg.data.WorldData;
import knokko.rpg.entity.data.EntityEyeGroupData;
import knokko.rpg.entity.data.EntityMonkeyGroupData;
import knokko.util.EntityUtils;
import knokko.util.ExtraUtils;
import knokko.util.Position;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public abstract class EntityEye extends EntityCreature{
	
	public EntityEyeGroupData groupdata;
	public String groupId;
	public Position position;
	private int pathCooldown;
	private int strikeCooldown;
	
	public EntityEye(World world) {
		super(world);
	}
	public void onUpdate(){
		super.onUpdate();
		followTarget();
		attackTarget();
		if(entityToAttack != null && pathCooldown <= 0 && entityToAttack.onGround){
			setPathToEntity(worldObj.getPathEntityToEntity(this, entityToAttack, 64, false, false, false, true));
			pathCooldown = 30;
		}
		if(entityToAttack != null && position != null){
			double height = entityToAttack.posY - posY;
			double distance = position.getSquaredDistance(new Position(entityToAttack));
			double pitch = -Math.toDegrees(Math.asin(ExtraUtils.divineAccurate(height, distance)));
			rotationPitch = (float) pitch;
		}
		else if(pathCooldown > 0){
			--pathCooldown;
		}
		if(groupdata == null && !worldObj.isRemote){
			if(groupId != null && !groupId.isEmpty()){
				groupdata = (EntityEyeGroupData) EntityUtils.getEntityByUUID(worldObj, groupId);
			}
			else {
				if(ticksExisted > 10){
					EntityEyeGroupData group = (EntityEyeGroupData) EntityUtils.getNearestEntityInList(position, WorldData.get(worldObj).eyeTeams);
					if(group == null && ticksExisted > 50){
						group = new EntityEyeGroupData(worldObj);
						position.spawnEntity(group, worldObj);
						groupdata = group;
					}
					else {
						groupdata = group;
					}
				}
			}
		}
		if(getHealth() < 30){
			if(groupdata != null){
				if(groupdata.hurtEye == null){
					groupdata.hurtEye = this;
				}
			}
		}
		if(strikeCooldown > 0){
			--strikeCooldown;
		}
		position = new Position(this);
	}
	@Override
	public void writeEntityToNBT(NBTTagCompound nbt){
		super.writeEntityToNBT(nbt);
		if(groupdata != null){
			nbt.setString("group", groupdata.getUniqueID().toString());
		}
	}
	@Override
	public void readEntityFromNBT(NBTTagCompound nbt){
		super.readEntityFromNBT(nbt);
		groupId = nbt.getString("group");
	}
	public boolean canDespawn(){
		return false;
	}
	public void followTarget(){
		if(groupdata != null && position != null){
			entityToAttack = EntityUtils.getNearestEntityInList(position, groupdata.targets);
			if(entityToAttack == null && position.getSquaredDistance(new Position(groupdata)) > 32 && !hasPath()){
				setPathToEntity(worldObj.getPathEntityToEntity(this, groupdata, 64, false, false, false, true));
			}
			if(entityToAttack == null && position.getSquaredDistance(new Position(groupdata)) < 16 && hasPath()){
				setPathToEntity(null);
			}
		}
	}
	public void attackTarget(){
			if(entityToAttack != null && position != null && !worldObj.isRemote){
				if(position.getSquaredDistance(new Position(entityToAttack)) <= 3 && strikeCooldown <= 0){
					entityToAttack.attackEntityFrom(DamageSource.causeMobDamage(this), 5);
					strikeCooldown = 10;
				}
			}
	}
	public boolean attackEntityFrom(DamageSource damage, float f){
		if(damage.getEntity() != null && damage.getEntity() instanceof EntityLivingBase && groupdata != null){
			if(!groupdata.targets.contains(damage.getEntity())){
				groupdata.targets.add(damage.getEntity());
			}
		}
		return super.attackEntityFrom(damage, f);
	}
	
	public void applyEntityAttributes(){
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(30);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.6);
	}
}
