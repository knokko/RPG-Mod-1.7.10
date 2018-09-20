package knokko.rpg.items;

import java.util.List;

import knokko.rpg.data.WorldData;
import knokko.rpg.entity.minion.EntityChow;
import knokko.rpg.entity.minion.FightType;
import knokko.rpg.entity.minion.TargetType;
import knokko.rpg.entity.monster.EntityLavaShark;
import knokko.rpg.main.KnokkoRPG;
import knokko.rpg.main.s;
import knokko.rpg.worldgen.empty.EmptyTeleporter;
import knokko.util.BlockUtils;
import knokko.util.ExtraUtils;
import knokko.util.Line;
import knokko.util.Position;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.EntityHeartFX;
import net.minecraft.client.particle.EntitySpellParticleFX;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class TestItem extends Item {
	public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player){
		player.swingItem();
		if(!world.isRemote){
			player.travelToDimension(KnokkoRPG.fireLandsId);
			player.timeUntilPortal = 1000;
		}
		return item;
	}
	
	public TestItem(){
		setTextureName("stick");
		setUnlocalizedName("testitem");
		setCreativeTab(CreativeTabs.tabMisc);
	}
}
