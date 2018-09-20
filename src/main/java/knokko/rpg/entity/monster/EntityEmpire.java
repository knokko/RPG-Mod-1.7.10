package knokko.rpg.entity.monster;

import knokko.rpg.entity.projectile.EntityExplosiveSpell;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityEmpire extends EntityMob{
	public int cooldown = 100;
	public ItemStack wand = new ItemStack(Items.blaze_rod);
	
	public EntityEmpire(World world) {
		super(world);
		ItemStack boots = new ItemStack(Items.diamond_boots);
		ItemStack leggings = new ItemStack(Items.diamond_leggings);
		ItemStack chestplate = new ItemStack(Items.diamond_chestplate);
		ItemStack helmet = new ItemStack(Items.diamond_helmet);
		boots.addEnchantment(Enchantment.blastProtection, 4);
		leggings.addEnchantment(Enchantment.protection, 4);
		chestplate.addEnchantment(Enchantment.protection, 4);
		helmet.addEnchantment(Enchantment.blastProtection, 4);
		wand.addEnchantment(Enchantment.fireAspect, 2);
		setCurrentItemOrArmor(1, boots);
		setCurrentItemOrArmor(2, leggings);
		setCurrentItemOrArmor(3, chestplate);
		setCurrentItemOrArmor(4, helmet);
	}
	
	public EntityEmpire(World world, int xCoord, int yCoord, int zCoord) {
		super(world);
		setPosition(xCoord, yCoord, zCoord);
	}

	public void onUpdate(){
		super.onUpdate();
		EntityExplosiveSpell spell = new EntityExplosiveSpell(worldObj, this, 4);
		spell.power = 3;
		if(cooldown > 0 && !worldObj.isRemote){
			--cooldown;
		}
		if(cooldown == 0 && !worldObj.isRemote && entityToAttack != null){
			cooldown = 200;
			worldObj.spawnEntityInWorld(spell);
			swingItem();
		}
		if(entityToAttack instanceof EntityEmpire){
			entityToAttack = null;
		}
	}
	public ItemStack getHeldItem(){
		return wand;
	}
	public void applyEntityAttributes(){
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.2);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(48);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(1);
	}
}
