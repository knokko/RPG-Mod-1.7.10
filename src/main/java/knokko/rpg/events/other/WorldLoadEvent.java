package knokko.rpg.events.other;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.world.GameRules;
import net.minecraftforge.event.world.WorldEvent.Load;

public class WorldLoadEvent {
	
	@SubscribeEvent
	public void onWorldLoad(Load event){
		GameRules rules = event.world.getGameRules();
		if(!rules.hasRule("rpgExplosions"))
			rules.addGameRule("rpgExplosions", "True");
		if(!rules.hasRule("rpgXpBlock"))
			rules.addGameRule("rpgXpBlock", "False");
	}
}
