package knokko.rpg.entity.render;

import knokko.rpg.main.s;
import net.minecraft.client.renderer.entity.RenderDragon;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderFireDragon extends RenderDragon{
	
	public ResourceLocation getEntityTexture(Entity entity){
		return new ResourceLocation(s.t + "textures/entities/dragon.png");
	}
}
