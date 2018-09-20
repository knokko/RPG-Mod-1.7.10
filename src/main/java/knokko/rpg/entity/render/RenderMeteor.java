package knokko.rpg.entity.render;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import knokko.rpg.entity.projectile.EntityMeteor;
import knokko.rpg.main.s;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.init.Items;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderMeteor extends Render{
	
	private float f;

    public RenderMeteor(float fl){
        f = fl;
    }
    public void doRender(EntityMeteor meteor, double x, double y, double z, float yaw, float ticktime)
    {
        GL11.glPushMatrix();
        bindEntityTexture(meteor);
        GL11.glTranslatef((float)x, (float)y, (float)z);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        float f2 = f;
        GL11.glScalef(f2 / 1, f2 / 1, f2 / 1);
        Tessellator tessellator = Tessellator.instance;
        GL11.glRotatef(180.0F - this.renderManager.playerViewY, 0, 1, 0);
        GL11.glRotatef(-this.renderManager.playerViewX, 1, 0, 0);
        tessellator.startDrawingQuads();
        tessellator.setNormal(0, 1, 0);
        tessellator.addVertexWithUV(-2, -2, 0, 0, 1);
        tessellator.addVertexWithUV(2, -2, 0, 1, 1);
        tessellator.addVertexWithUV(2, 2, 0, 1, 0);
        tessellator.addVertexWithUV(-2, 2, 0, 0, 0);
        tessellator.draw();
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glPopMatrix();
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    public ResourceLocation getEntityTexture(EntityMeteor meteor)
    {
        return new ResourceLocation(s.t + "textures/entities/meteor.png");
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return getEntityTexture((EntityMeteor)entity);
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void func_76986_a(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(Entity entity, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
    {
        this.doRender((EntityMeteor)entity, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }
}
