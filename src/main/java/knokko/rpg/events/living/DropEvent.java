package knokko.rpg.events.living;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.init.Items;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class DropEvent {
	@SubscribeEvent
	public void dropEvent(LivingDropsEvent event){
		if(event.entityLiving instanceof EntityPig){
		}
	}
}
