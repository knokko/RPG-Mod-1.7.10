package knokko.rpg.blocks.main;

import knokko.rpg.blocks.SimpleBlock;
import knokko.rpg.blocks.Table;
import knokko.rpg.blocks.TestBlock;
import knokko.rpg.blocks.VoidPortal;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class RPGBlocks {
	
	public static Table table;
	public static VoidPortal voidportal;
	public static SimpleBlock basalt;
	public static SimpleBlock ruby_ore;
	
	public static void registerBlocks(){
		table = new Table();
		voidportal = new VoidPortal();
		basalt = new SimpleBlock(Material.rock, "basalt", 4, 15, CreativeTabs.tabBlock, "pickaxe", 1);
		ruby_ore = new SimpleBlock(Material.rock, "rubyore", 5, 15, CreativeTabs.tabBlock, "pickaxe", 2);
		
		GameRegistry.registerBlock(table, "table");
		GameRegistry.registerBlock(voidportal, "voidportal");
		GameRegistry.registerBlock(basalt, "basalt");
		GameRegistry.registerBlock(ruby_ore, "rubyore");
	}
}
