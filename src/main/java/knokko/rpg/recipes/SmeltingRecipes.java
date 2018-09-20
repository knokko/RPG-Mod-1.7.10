package knokko.rpg.recipes;

import net.minecraft.item.ItemStack;
import knokko.rpg.blocks.main.RPGBlocks;
import knokko.rpg.items.main.RPGItems;
import cpw.mods.fml.common.registry.GameRegistry;

public class SmeltingRecipes {
	
	public static void load(){
		GameRegistry.addSmelting(RPGBlocks.ruby_ore, new ItemStack(RPGItems.ruby), 1);
	}
}
