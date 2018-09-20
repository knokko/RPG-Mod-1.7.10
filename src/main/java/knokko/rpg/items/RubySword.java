package knokko.rpg.items;

import knokko.rpg.items.main.RPGItems;
import knokko.rpg.main.s;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemSword;

public class RubySword extends ItemSword{

	public RubySword() {
		super(RPGItems.RUBY);
		setUnlocalizedName("rubysword");
		setTextureName(s.t + "rubysword");
		setCreativeTab(CreativeTabs.tabCombat);
	}
}
