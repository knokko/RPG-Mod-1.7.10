package knokko.rpg.recipes;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import knokko.rpg.items.main.RPGItems;

public class ShapedRecipes {
	public static final ItemStack woodwand = new ItemStack(RPGItems.woodWand);
	
	public static void load(){
		GameRegistry.addShapedRecipe(woodwand, "  l", " s ", "s  ", 'l', Items.wheat_seeds, 's', Items.stick);
	}
}
