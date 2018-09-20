package knokko.rpg.items;

import knokko.rpg.entity.minion.EntityNecromancerMinion;
import knokko.rpg.entity.minion.TargetType;
import knokko.rpg.main.s;
import knokko.util.Line;
import knokko.util.Position;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class SkullRod extends Item{
	
	public SkullRod(){
		setTextureName(s.t + "skullrod");
		setUnlocalizedName("skullrod");
		setCreativeTab(CreativeTabs.tabTools);
	}
	
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player){
		if(!world.isRemote){
			Line line = Line.fromRaytrace(player, 50).toNearestBlock(world, false, 1);
			EntityNecromancerMinion minion = (EntityNecromancerMinion) line.getNearestEntity(world, EntityNecromancerMinion.class, new Position(player), player);
			String name = stack.getDisplayName();
			if(minion != null && name != null && !name.isEmpty() && TargetType.isTargetType(name)){
				minion.targetType = TargetType.fromString(name);
			}
		}
		return stack;
	}
}
