package knokko.rpg.entity.render;

import knokko.rpg.main.s;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderLavaShark extends RenderLiving{

	public RenderLavaShark(ModelBase model, float shadowSize) {
		super(model, shadowSize);
	}

	@Override
	public ResourceLocation getEntityTexture(Entity entity) {
		return new ResourceLocation(s.t + "textures/entities/lavashark.png");
	}

}
