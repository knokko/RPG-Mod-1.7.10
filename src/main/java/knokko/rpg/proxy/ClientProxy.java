package knokko.rpg.proxy;

import knokko.rpg.entity.creature.EntityFireEye;
import knokko.rpg.entity.creature.EntityIceEye;
import knokko.rpg.entity.creature.EntityLifeEye;
import knokko.rpg.entity.creature.EntityMonkey;
import knokko.rpg.entity.data.EntityEyeGroupData;
import knokko.rpg.entity.data.EntityMonkeyGroupData;
import knokko.rpg.entity.data.UndeadTeam;
import knokko.rpg.entity.effect.EntityEnergyBall;
import knokko.rpg.entity.minion.EntityChow;
import knokko.rpg.entity.minion.EntityDreadLord;
import knokko.rpg.entity.minion.EntitySoar;
import knokko.rpg.entity.model.ModelDreadLord;
import knokko.rpg.entity.model.ModelEye;
import knokko.rpg.entity.model.ModelShark;
import knokko.rpg.entity.model.ModelTroll;
import knokko.rpg.entity.monster.EntityEmpire;
import knokko.rpg.entity.monster.EntityFireDragon;
import knokko.rpg.entity.monster.EntityLavaShark;
import knokko.rpg.entity.monster.EntityTroll;
import knokko.rpg.entity.projectile.EntityDarkPulse;
import knokko.rpg.entity.projectile.EntityExplosiveSpell;
import knokko.rpg.entity.projectile.EntityFireSpell;
import knokko.rpg.entity.projectile.EntityIceSpell;
import knokko.rpg.entity.projectile.EntityLaser;
import knokko.rpg.entity.projectile.EntityMeteor;
import knokko.rpg.entity.render.RenderChow;
import knokko.rpg.entity.render.RenderDreadLord;
import knokko.rpg.entity.render.RenderEmpire;
import knokko.rpg.entity.render.RenderEye;
import knokko.rpg.entity.render.RenderFireDragon;
import knokko.rpg.entity.render.RenderInvisible;
import knokko.rpg.entity.render.RenderLavaShark;
import knokko.rpg.entity.render.RenderMeteor;
import knokko.rpg.entity.render.RenderMonkey;
import knokko.rpg.entity.render.RenderSoar;
import knokko.rpg.entity.render.RenderTroll;
import knokko.rpg.tileentity.TileEntityTable;
import knokko.rpg.tileentity.render.TableRenderer;
import net.minecraft.client.model.ModelBiped;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends ServerProxy{
	
	@Override
	public void registerRenderThings(){
		RenderingRegistry.registerEntityRenderingHandler(EntityLifeEye.class, new RenderEye(new ModelEye(), 0.3F));
		RenderingRegistry.registerEntityRenderingHandler(EntityIceEye.class, new RenderEye(new ModelEye(), 0.3F));
		RenderingRegistry.registerEntityRenderingHandler(EntityFireEye.class, new RenderEye(new ModelEye(), 0.3F));
		RenderingRegistry.registerEntityRenderingHandler(EntityMeteor.class, new RenderMeteor(1));
		RenderingRegistry.registerEntityRenderingHandler(EntityEyeGroupData.class, new RenderInvisible());
		RenderingRegistry.registerEntityRenderingHandler(UndeadTeam.class, new RenderInvisible());
		RenderingRegistry.registerEntityRenderingHandler(EntityEnergyBall.class, new RenderInvisible());
		RenderingRegistry.registerEntityRenderingHandler(EntityIceSpell.class, new RenderInvisible());
		RenderingRegistry.registerEntityRenderingHandler(EntityMonkeyGroupData.class, new RenderInvisible());
		RenderingRegistry.registerEntityRenderingHandler(EntityTroll.class, new RenderTroll(new ModelTroll(0, 0, 128, 64), 1));
		RenderingRegistry.registerEntityRenderingHandler(EntityMonkey.class, new RenderMonkey(new ModelBiped(), 0.3f));
		RenderingRegistry.registerEntityRenderingHandler(EntityExplosiveSpell.class, new RenderInvisible());
		RenderingRegistry.registerEntityRenderingHandler(EntityEmpire.class, new RenderEmpire(new ModelBiped(), 0.3F));
		RenderingRegistry.registerEntityRenderingHandler(EntityFireSpell.class, new RenderInvisible());
		RenderingRegistry.registerEntityRenderingHandler(EntityDarkPulse.class, new RenderInvisible());
		RenderingRegistry.registerEntityRenderingHandler(EntityLaser.class, new RenderInvisible());
		RenderingRegistry.registerEntityRenderingHandler(EntityChow.class, new RenderChow(new ModelBiped(), 0.3F));
		RenderingRegistry.registerEntityRenderingHandler(EntityDreadLord.class, new RenderDreadLord(new ModelDreadLord(0, 0, 64, 32), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntitySoar.class, new RenderSoar(new ModelBiped(), 0.3F));
		RenderingRegistry.registerEntityRenderingHandler(EntityFireDragon.class, new RenderFireDragon());
		RenderingRegistry.registerEntityRenderingHandler(EntityLavaShark.class, new RenderLavaShark(new ModelShark(), 3));
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTable.class, new TableRenderer());
	}
}