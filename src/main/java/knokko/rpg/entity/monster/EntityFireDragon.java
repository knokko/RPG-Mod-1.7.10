package knokko.rpg.entity.monster;

import knokko.rpg.entity.projectile.EntityFireSpell;
import knokko.util.ExtraUtils;
import knokko.util.Line;
import knokko.util.Position;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityFireDragon extends EntityDragon{

	public EntityFireDragon(World world) {
		super(world);
		
	}
	
	protected int fireCooldown;
	
	public void onUpdate(){
		super.onUpdate();
		if(!worldObj.isRemote){
			if(fireCooldown <= 0){
				Position position = new Position(this);
				Position target = new Position(targetX, targetY, targetZ);
				Line line = new Line(position, target);
				EntityFireSpell fire = new EntityFireSpell(worldObj);
				fireCooldown = 40;
				fire.motionX = ExtraUtils.divineAccurate(line.distanceXT, line.distance);
				fire.motionY = ExtraUtils.divineAccurate(line.distanceYT, line.distance);
				fire.motionZ = ExtraUtils.divineAccurate(line.distanceZT, line.distance);
				position.spawnEntity(fire, worldObj);
			}
			else {
				--fireCooldown;
			}
		}
	}
	public void onDeathUpdate(){
		setDead();
	}
	
	public boolean attackEntityFrom(DamageSource source, float damage){
		return super.func_82195_e(source, damage);
	}
}
