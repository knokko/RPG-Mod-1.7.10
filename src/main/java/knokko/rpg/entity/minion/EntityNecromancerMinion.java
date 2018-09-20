package knokko.rpg.entity.minion;

import knokko.rpg.data.WorldData;
import knokko.rpg.entity.data.UndeadTeam;
import knokko.util.EntityUtils;
import knokko.util.ExtraUtils;
import knokko.util.Position;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public abstract class EntityNecromancerMinion extends EntityCreature{
	/**
	 * The master of the mob. This mob will fight for it's master as long as it is a necromancer.
	 */
	public Entity master;
	/**
	 * The UUID of the master as String.
	 */
	public String masterId;
	/**
	 * The current position of this mob.
	 */
	public Position position;
	/**
	 * The way this mob will choose its target.
	 */
	public TargetType targetType;
	/**
	 * The team of the minion, the master of the minion is always the leader of the team.
	 * Every minion of the master is in the same team.
	 */
	public UndeadTeam team;
	
	private int attributeTimer;
	
	private int pathCooldown = 0;
	private int strikeCooldown;
	
	public EntityNecromancerMinion(World world) {
		super(world);
	}
	
	public EntityNecromancerMinion(Entity owner){
		this(owner, new Position(owner));
	}
	
	public EntityNecromancerMinion(Entity owner, Position spawn){
		this(owner, TargetType.fromString(WorldData.getString(owner, "standartnecromancytarget", "helper")), spawn);
	}
	
	public EntityNecromancerMinion(Entity owner, TargetType type, Position spawn){
		super(owner.worldObj);
		setPosition(spawn.x, spawn.y, spawn.z);
		master = owner;
		team = UndeadTeam.getTeam(owner);
		targetType = type;
	}
	
	public EntityNecromancerMinion(Entity owner, double x, double y, double z){
		this(owner, new Position(x, y, z));
	}
	
	public EntityNecromancerMinion(Entity owner, TargetType type, double x, double y, double z){
		this(owner, type, new Position(x, y, z));
	}
	
	public EnumCreatureAttribute getCreatureAttribute(){
		return EnumCreatureAttribute.UNDEAD;
	}
	
	public boolean canDespawn(){
		return false;
	}
	
	public void writeEntityToNBT(NBTTagCompound nbt){
		super.writeEntityToNBT(nbt);
		if(master != null){
			nbt.setString("master", master.getUniqueID().toString());
		}
		if(targetType != null){
			targetType.writeToNBT(nbt);
		}
	}
	
	public void readEntityFromNBT(NBTTagCompound nbt){
		super.readEntityFromNBT(nbt);
		masterId = nbt.getString("master");
		targetType = TargetType.fromNBT(nbt);
	}
	
	public void onUpdate(){
		super.onUpdate();
		position = new Position(this);
		if(!worldObj.isRemote){
			if(master == null){
				if(masterId != null && !masterId.isEmpty()){
					master = EntityUtils.getEntityByUUID(worldObj, masterId);
				}
			}
			if(team == null && master != null && !worldObj.isRemote){
				team = UndeadTeam.getTeam(master);
				if(ticksExisted > 50 && team == null){
					worldObj.spawnEntityInWorld(new UndeadTeam(master));
				}
			}
			if(team != null){
				if(!team.members.contains(this)){
					team.members.add(this);
				}
				if(!team.members.contains(master) && master != null){
					team.members.add(master);
				}
				entityToAttack = team.getTarget(this);
			}
			if(entityToAttack != null && position != null){
				double height = (entityToAttack.posY + motionY) - posY;
				double distance = position.getSquaredDistance(new Position(entityToAttack));
				double pitch = -Math.toDegrees(Math.asin(ExtraUtils.divineAccurate(height, distance)));
				rotationPitch = (float) pitch;
				faceEntity(entityToAttack, 100, 0);
				if(getFightType() == FightType.CLOSECOMBAT){
					if((!hasPath() || pathCooldown == 0) && !worldObj.isRemote){
						pathCooldown = 40;
						setPathToEntity(worldObj.getPathEntityToEntity(this, entityToAttack, (float) getEntityAttribute(SharedMonsterAttributes.followRange).getAttributeValue(), false, false, true, true));
					}
				}
				if(entityToAttack.isDead){
					entityToAttack = null;
				}
			}
			if(attributeTimer <= 0 && master != null){
				applyEntityAttributes(master);
				attributeTimer = 100;
			}
			if(strikeCooldown > 0){
				--strikeCooldown;
			}
			if((getFightType() == FightType.CLOSECOMBAT || getFightType() == FightType.MULTIPLY) && (!worldObj.isRemote && entityToAttack != null)){
				if(Position.getSquaredDistance(new Position(this), new Position(entityToAttack)) < 3 && strikeCooldown <= 0){
					entityToAttack.attackEntityFrom(DamageSource.causeMobDamage(this), (float) getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue());
					strikeCooldown = 10;
				}
			}
			if(master != null){
				double distance = Position.getSquaredDistance(new Position(this), new Position(master));
				if(targetType != null && targetType.equals(TargetType.GUARDIAN)){
					if(distance > 16 && (!hasPath() || pathCooldown == 0)){
						pathCooldown = 20;
						setPathToEntity(worldObj.getPathEntityToEntity(this, master, 64, false, false, false, true));
					}
				}
				if(distance > 32 && entityToAttack == null && !hasPath() && !isGhost()){
					setPathToEntity(worldObj.getPathEntityToEntity(this, master, (float) getEntityAttribute(SharedMonsterAttributes.followRange).getAttributeValue(), false, false, true, true));
				}
				if(distance > 48 && pathCooldown <= 0 && !isGhost()){
					setPathToEntity(worldObj.getPathEntityToEntity(this, master, (float) getEntityAttribute(SharedMonsterAttributes.followRange).getAttributeValue(), false, false, true, true));
					pathCooldown = 20;
				}
			}
			if((getFightType() == FightType.RANGED || getFightType() == FightType.MULTIPLY) && entityToAttack != null){
				rangedUpdate();
			}
		}
		onExtraUpdate();
	}
	/**
	 * How this entity will fight. It can be ranged, close combat or mulitply.
	 * @return How this entity will fight.
	 */
	public FightType getFightType(){
		return FightType.CLOSECOMBAT;
	}
	
	public boolean attackEntityFrom(DamageSource source, float damage){
		Entity entity = source.getEntity();
		if(entity instanceof EntityNecromancerMinion){
			EntityNecromancerMinion minion = (EntityNecromancerMinion) entity;
			if(master != null && master == minion.master){
				return false;
			}
			if(master != null && minion.master != null){
				if(WorldData.isOnSameTeam(master, minion.master)){
					return false;
				}
			}
		}
		else if(entity == master && master != null){
			return false;
		}
		else if(entity instanceof EntityLivingBase && team != null){
			team.addTarget(entity, false, true);
		}
		return super.attackEntityFrom(source, damage);
	}
	
	public void applyEntityAttributes(){
		super.applyEntityAttributes();
		getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(64);
	}
	
	public abstract void applyEntityAttributes(Entity master);
	
	public boolean interact(EntityPlayer player){
		return true;
	}
	
	public boolean isGhost(){
		return false;
	}
	/**
	 * A method you can override to do extra things every tick.
	 */
	public void onExtraUpdate(){}
	/**
	 * A method that is called every tick if the targettype is ranged or multiply and entityToAttack isn't null.
	 */
	public void rangedUpdate(){}
}
