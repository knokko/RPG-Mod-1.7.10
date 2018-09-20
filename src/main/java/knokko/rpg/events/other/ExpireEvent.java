package knokko.rpg.events.other;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.entity.item.ItemExpireEvent;

public class ExpireEvent {
	@SubscribeEvent
	public void itemExpireEvent(ItemExpireEvent event){
		event.setCanceled(true);
	}
}
