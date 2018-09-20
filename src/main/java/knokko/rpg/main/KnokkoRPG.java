package knokko.rpg.main;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.DimensionManager;
import knokko.rpg.blocks.main.RPGBlocks;
import knokko.rpg.tileentity.RPGTileEntities;
import knokko.rpg.command.CommandNecromancy;
import knokko.rpg.command.CommandRPG;
import knokko.rpg.command.CommandUUID;
import knokko.rpg.entity.main.RPGentities;
import knokko.rpg.events.main.RPGEvents;
import knokko.rpg.items.main.RPGItems;
import knokko.rpg.packet.LineMessage;
import knokko.rpg.packet.NBTMessage;
import knokko.rpg.proxy.ServerProxy;
import knokko.rpg.recipes.RecipeHandler;
import knokko.rpg.worldgen.RPGGenerator;
import knokko.rpg.worldgen.empty.EmptyWorldProvider;
import knokko.rpg.worldgen.firelands.FireLandsProvider;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;

@Mod(modid= s.i, name= s.n, version= s.v)
public class KnokkoRPG {
	
	public static SimpleNetworkWrapper network;
	public static int voidWorldId;
	public static int fireLandsId;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event){
		network = NetworkRegistry.INSTANCE.newSimpleChannel("knokko RPG");
		network.registerMessage(LineMessage.Handler.class, LineMessage.class, 0, Side.CLIENT);
		network.registerMessage(NBTMessage.Handler.class, NBTMessage.class, 1, Side.CLIENT);
		RPGItems.load();
		RPGItems.registerItems();
		RPGBlocks.registerBlocks();
	}
	@EventHandler
	public void init(FMLInitializationEvent event){
		RPGEvents.registerEvents();
		RPGentities.registerEntities();
		RPGTileEntities.registerTileEntities();
		RecipeHandler.load();
		proxy.registerRenderThings();
		proxy.registerKeyBindings();
		GameRegistry.registerWorldGenerator(new RPGGenerator(), 1);
		voidWorldId = DimensionManager.getNextFreeDimId();
		DimensionManager.registerProviderType(voidWorldId, EmptyWorldProvider.class, false);
		DimensionManager.registerDimension(voidWorldId, voidWorldId);
		fireLandsId = DimensionManager.getNextFreeDimId();
		DimensionManager.registerProviderType(fireLandsId, FireLandsProvider.class, false);
		DimensionManager.registerDimension(fireLandsId, fireLandsId);
	}
	@EventHandler
	public void postInit(FMLPostInitializationEvent event){
	}
	@EventHandler
	public void serverEvent(FMLServerStartingEvent event){
		event.registerServerCommand(new CommandRPG());
		event.registerServerCommand(new CommandUUID());
		event.registerServerCommand(new CommandNecromancy());
	}
	@Instance(s.i)
	public static KnokkoRPG modInstance;
	
	@SidedProxy(clientSide = "knokko.rpg.proxy.ClientProxy", serverSide = "knokko.rpg.proxy.ServerProxy")
	public static ServerProxy proxy;
	
	public static NBTTagCompound data = new NBTTagCompound();
}
