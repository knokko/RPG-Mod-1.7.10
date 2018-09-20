package knokko.rpg.blocks;

import knokko.rpg.main.s;
import knokko.rpg.tileentity.TileEntityTest;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TestBlock extends BlockContainer{

	public TestBlock() {
		super(Material.rock);
		setBlockTextureName(s.t + "test");
		setBlockName("test");
		setCreativeTab(CreativeTabs.tabMisc);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int i) {
		return new TileEntityTest();
	}
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float f1, float f2, float f3){
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		if(tileEntity instanceof TileEntityTest){
			TileEntityTest test = (TileEntityTest) tileEntity;
			player.func_146100_a(test);
			test.activated(player);
			return true;
		}
		else {
			return false;
		}
    }
}
