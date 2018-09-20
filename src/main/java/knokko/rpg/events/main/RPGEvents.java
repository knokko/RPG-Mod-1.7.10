package knokko.rpg.events.main;

import cpw.mods.fml.common.FMLCommonHandler;
import knokko.rpg.events.living.*;
import knokko.rpg.events.other.*;
import net.minecraftforge.common.MinecraftForge;

public class RPGEvents {
	public static void registerEvents(){
		MinecraftForge.EVENT_BUS.register(new HurtEvent());
		MinecraftForge.EVENT_BUS.register(new DeathEvent());
		MinecraftForge.EVENT_BUS.register(new DropEvent());
		MinecraftForge.EVENT_BUS.register(new ExpireEvent());
		MinecraftForge.EVENT_BUS.register(new UpdateEvent());
		MinecraftForge.EVENT_BUS.register(new ChatEvent());
		MinecraftForge.EVENT_BUS.register(new JoinEvent());
		MinecraftForge.EVENT_BUS.register(new FOVHandler());
		MinecraftForge.EVENT_BUS.register(new WorldLoadEvent());
		FMLCommonHandler.instance().bus().register(new TickEvent());
	}
}
