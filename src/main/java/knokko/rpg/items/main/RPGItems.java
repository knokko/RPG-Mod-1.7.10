package knokko.rpg.items.main;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;
import knokko.rpg.items.BananaSword;
import knokko.rpg.items.ItemWand;
import knokko.rpg.items.RubySword;
import knokko.rpg.items.SimpleItem;
import knokko.rpg.items.SkullRod;
import knokko.rpg.items.TestItem;
import cpw.mods.fml.common.registry.GameRegistry;

public class RPGItems {
	
	public static final ToolMaterial RUBY = EnumHelper.addToolMaterial("ruby", 3, 2000, 3, 3, 20);
	
	public static TestItem testitem;
	public static ItemWand woodWand;
	public static BananaSword bananaSword;
	public static SkullRod skullRod;
	public static SimpleItem ruby;
	public static RubySword ruby_sword;
	
	public static void load(){
		testitem = new TestItem();
		woodWand = new ItemWand(0.5, 2, "woodwand");
		bananaSword = new BananaSword(4, 0, false);
		skullRod = new SkullRod();
		ruby = new SimpleItem("ruby", CreativeTabs.tabMaterials);
		ruby_sword = new RubySword();
	}
	
	public static void registerItems(){
		GameRegistry.registerItem(testitem, "testitem");
		GameRegistry.registerItem(woodWand, "woodwand");
		GameRegistry.registerItem(bananaSword, "bananasword");
		GameRegistry.registerItem(skullRod, "skullrod");
		GameRegistry.registerItem(ruby, "ruby");
		GameRegistry.registerItem(ruby_sword, "rubysword");
	}
}
