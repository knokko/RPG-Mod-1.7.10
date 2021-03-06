package knokko.rpg.events.living;

import java.util.Random;

import knokko.rpg.RPG;
import knokko.rpg.data.WorldData;
import knokko.rpg.entity.data.UndeadTeam;
import knokko.rpg.entity.effect.EntityBlood;
import knokko.rpg.entity.minion.EntityNecromancerMinion;
import knokko.rpg.entity.monster.EntityFireDragon;
import knokko.util.BlockUtils;
import knokko.util.EntityUtils;
import knokko.util.ExtraUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class HurtEvent {
	@SubscribeEvent
	public void hurtEvent(LivingHurtEvent event){
		World world = event.entityLiving.worldObj;
		if(event.entityLiving != null){
			if(event.source == DamageSource.fall){
				event.setCanceled(true);
			}
			if(event.source.getEntity() instanceof EntityNecromancerMinion){
				EntityNecromancerMinion minion = (EntityNecromancerMinion) event.source.getEntity();
				if(minion.master == event.entityLiving){
					event.setCanceled(true);
				}
			}
			if(event.source.getEntity() instanceof EntityPlayer){
				EntityPlayer player = (EntityPlayer) event.source.getEntity();
				UndeadTeam team = UndeadTeam.getTeam(player);
				if(team != null){
					team.addTarget(event.entityLiving, false, false);
				}
				String Class = WorldData.getString(player, "class", "hunter");
				if(event.entityLiving instanceof EntityPlayer){
					EntityPlayer player1 = (EntityPlayer) event.entityLiving;
					if(WorldData.isOnSameTeam(player1, player)){
						event.setCanceled(true);
					}
				}
				if(event.source.damageType.matches("player") && !event.isCanceled()){
					if(WorldData.getString(player, "class", "hunter").matches("paladin") || WorldData.getString(player, "class", "hunter").matches("magicwarrior")){
						if(WorldData.getInteger(player, "mana") >= 5000 && WorldData.isIntegerPositive(player, "spiritslash")){
							WorldData.removeFromInteger(player, "mana", 5000, 0);
							event.ammount *= RPG.getPlayerSpirit(player);
							EntityUtils.sendManaMessage(player);
						}
						else {
							event.ammount *= RPG.getPlayerStrengt(player);
						}
					}
					else {
						event.ammount *= RPG.getPlayerStrengt(player);
					}
					if(WorldData.isIntegerPositive(player, "poisonblade") && player.getCurrentEquippedItem() != null && Class.matches("hunter")){
						if(player.getCurrentEquippedItem().getItem() instanceof ItemSword){
							event.entityLiving.addPotionEffect(new PotionEffect(19, 600));
						}
					}
					if(WorldData.isIntegerPositive(player, "powerattack") && (Class.matches("warrior") || Class.matches("berserker") || Class.matches("magicwarrior"))){
						if(WorldData.getInteger(player, "mana") >= 2000){
							event.ammount *= 1.5;
							WorldData.removeFromInteger(player, "mana", 2000, 0);
							EntityUtils.sendManaMessage(player);
						}
					}
					if(Class.matches("brawler")){
						if(WorldData.isIntegerPositive(player, "windpunch") && player.getCurrentEquippedItem() == null){
							event.entityLiving.motionX = (event.entityLiving.posX - player.posX) * 5;
							event.entityLiving.motionY = (event.entityLiving.posX - player.posY) * 5;
							event.entityLiving.motionZ = (event.entityLiving.posZ - player.posZ) * 5;
						}
						if(WorldData.isIntegerPositive(player, "firepunch") && player.getCurrentEquippedItem() == null){
							event.entityLiving.setFire(5);
						}
						if(WorldData.isIntegerPositive(player, "icepunch") && player.getCurrentEquippedItem() == null){
							event.entityLiving.addPotionEffect(new PotionEffect(2, 100, 1));
						}
					}
					if(Class.matches("paladin")){
						if(WorldData.isIntegerPositive(player, "smite") && event.entityLiving.getCreatureAttribute() == EnumCreatureAttribute.UNDEAD){
							if(WorldData.getInteger(player, "mana") >= 5000){
								event.ammount *= 2;
								WorldData.removeFromInteger(player, "mana", 5000, 0);
								EntityUtils.sendManaMessage(player);
							}
						}
					}
				}
				if(event.source.getSourceOfDamage() instanceof EntityArrow && !event.isCanceled()){
					event.ammount *= RPG.getArcherPower(player);
					EntityArrow arrow = (EntityArrow) event.source.getSourceOfDamage();
					if(Class.matches("archer") || Class.matches("magicarcher")){
						if(WorldData.isIntegerPositive(arrow, "poisoned arrow")){
							event.entityLiving.addPotionEffect(new PotionEffect(19, 200, 1));
							WorldData.removeTimer(arrow, "poisoned arrow");
						}
						if(WorldData.isIntegerPositive(arrow, "cursed arrow") && Class.matches("magicarcher")){
							event.entityLiving.addPotionEffect(new PotionEffect(2, 200, 2));
							event.entityLiving.addPotionEffect(new PotionEffect(20, 200, 2));
							event.entityLiving.addPotionEffect(new PotionEffect(15, 200));
							WorldData.removeTimer(arrow, "cursed arrow");
						}
					}
					WorldData.removeTag(arrow);
				}
			}
			if(!event.isCanceled()) {
				if(event.source.damageType.matches("mob") && event.source.getEntity() instanceof EntityFireDragon){
					event.ammount *= 3;
				}
				float damage2 = event.ammount;
				damage2 = EntityUtils.applyArmorCalculations(event.entityLiving, event.source, damage2);
				damage2 = EntityUtils.applyPotionCalculations(event.entityLiving, event.source, damage2);
				int damage = ExtraUtils.fromDouble(damage2);
				if(event.entityLiving instanceof EntityPlayer){
					String damageString = event.source.damageType;
					UndeadTeam team = UndeadTeam.getTeam(event.entityLiving);
					if(team != null){
						team.addTarget(event.source.getEntity(), true, true);
					}
					if(damageString.matches("explosion.player")){
						damageString = "explosion";
					}
					EntityPlayer hurtPlayer = (EntityPlayer) event.entityLiving;
					if(event.source.getEntity() != null){
						if(damageString.matches("player") || damageString.matches("mob")){
							if(WorldData.getBooleanOption(hurtPlayer, "hurtMessages", true)){
								hurtPlayer.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.RED + "You are hurt for " + damage2 + " damage by " + event.source.getEntity().getCommandSenderName()));
							}
						}
						else {
							if(WorldData.getBooleanOption(hurtPlayer, "hurtMessages", true)){
								hurtPlayer.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.RED + "You are hurt for " + damage2 + " damage by " + damageString + " of "+ event.source.getEntity().getCommandSenderName()));
							}
						}
					}
					else {
						if(WorldData.getBooleanOption(hurtPlayer, "hurtMessages", true)){
							hurtPlayer.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.RED + "You are hurt for " + damage2 + " damage by " + damageString));
						}
					}
				}
				if(event.source.getEntity() instanceof EntityPlayer){
					EntityPlayer player = (EntityPlayer) event.source.getEntity();
					String Class = WorldData.getString(player, "class", "hunter");
					WorldData.addXP(player, damage);
					WorldData.addRaceXp(player, (int) (damage * 0.3));
					if(WorldData.getBooleanOption(player, "hitMessages", true)){
						player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.GREEN + "You have hit " + event.entityLiving.getCommandSenderName() + " for " + damage2 + " damage."));
					}
					if(Class.matches("magicwarrior")){
						if(WorldData.isIntegerPositive(player, "fireslash") && WorldData.getInteger(player, "mana") >= 1000){
							event.entityLiving.setFire(5);
							WorldData.removeFromInteger(player, "mana", 1000, 0);
							if(WorldData.getBooleanOption(player, "manaMessages", true) && !world.isRemote){
								player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.BLUE + "Your mana is " + WorldData.getInteger(player, "mana") + "/" + WorldData.getInteger(player, "maxmana")));
							}
						}
						if(WorldData.isIntegerPositive(player, "electricslash") && WorldData.getInteger(player, "mana") >= 1000){
							WorldData.removeFromInteger(player, "mana", 500, damage);
							WorldData.setInteger(event.entityLiving, 20, "paralyzed");
							if(WorldData.getBooleanOption(player, "manaMessages", true) && !world.isRemote){
								player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.BLUE + "Your mana is " + WorldData.getInteger(player, "mana") + "/" + WorldData.getInteger(player, "maxmana")));
							}
						}
						if(WorldData.isIntegerPositive(player, "blooddrain") && WorldData.getInteger(player, "mana") >= 3000){
							WorldData.removeFromInteger(player, "mana", 3000, 0);
							player.heal((float) (damage2 * 0.5));
							if(WorldData.getBooleanOption(player, "manaMessages", true) && !world.isRemote){
								player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.BLUE + "Your mana is " + WorldData.getInteger(player, "mana") + "/" + WorldData.getInteger(player, "maxmana")));
							}
						}
						if(WorldData.isIntegerPositive(player, "darkslash") && WorldData.getInteger(player, "mana") >= 1000){
							WorldData.removeFromInteger(player, "mana", 1000, damage);
							event.entityLiving.addPotionEffect(new PotionEffect(15, 100));
							if(WorldData.getBooleanOption(player, "manaMessages", true) && !world.isRemote){
								player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.BLUE + "Your mana is " + WorldData.getInteger(player, "mana") + "/" + WorldData.getInteger(player, "maxmana")));
							}
						}
					}
				}
				if(event.entityLiving instanceof EntityCreature || event.entityLiving instanceof EntityPlayer){
					int i = 0;
					while(i <= damage && i <= event.entityLiving.getHealth()){
						Random random = new Random();
						double x = event.entityLiving.posX - 2 + (random.nextInt(5) * random.nextDouble());
						double y = event.entityLiving.posY + random.nextInt(3) * random.nextDouble();
						double z = event.entityLiving.posZ - 2 + (random.nextInt(5) * random.nextDouble());
						event.entityLiving.worldObj.spawnEntityInWorld(new EntityBlood(event.entityLiving.worldObj, x, y, z, 3));
						++i;
					}
				}
				if(event.entityLiving instanceof EntityPlayer){
					EntityPlayer player = (EntityPlayer) event.entityLiving;
					if(WorldData.getString(player, "race", "human").matches("enderman")){
						int times = 0;
						while(times < 50){
							Random random = new Random();
							double x = player.posX - 10 + (random.nextDouble() * 20);
							double z = player.posZ - 10 + (random.nextDouble() * 20);
							double y = BlockUtils.getHighestSafePointForEnderman(world, (int)x, (int)z) + 1;
							if(y > 0){
								player.setPositionAndUpdate(x, y, z);
								times = 50;
							}
							++times;
						}
						if(event.source.getDamageType().matches("arrow")){
							event.setCanceled(true);
						}
					}
				}
			}
		}
	}
}
