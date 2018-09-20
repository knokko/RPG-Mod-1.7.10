package knokko.rpg.entity.main;

import knokko.rpg.entity.creature.EntityFireEye;
import knokko.rpg.entity.creature.EntityIceEye;
import knokko.rpg.entity.creature.EntityLifeEye;
import knokko.rpg.entity.creature.EntityMonkey;
import knokko.rpg.entity.data.EntityEyeGroupData;
import knokko.rpg.entity.data.EntityMonkeyGroupData;
import knokko.rpg.entity.data.UndeadTeam;
import knokko.rpg.entity.effect.EntityBlood;
import knokko.rpg.entity.effect.EntityEnergyBall;
import knokko.rpg.entity.minion.EntityChow;
import knokko.rpg.entity.minion.EntityDreadLord;
import knokko.rpg.entity.minion.EntitySoar;
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

public class RPGentities {
	public static void registerEntities(){
		EntityHandler.registerEntity(EntityBlood.class, "Blood");
		EntityHandler.registerEntity(EntityEnergyBall.class, "EnergyBall");
		EntityHandler.registerEntity(EntityEyeGroupData.class, "EyeGroupData");
		EntityHandler.registerEntity(EntityMonkeyGroupData.class, "MonkeyGroupData");
		EntityHandler.registerEntity(UndeadTeam.class, "UndeadTeam");
		EntityHandler.registerEntity(EntityMeteor.class, "Meteor");
		EntityHandler.registerCreatures(EntityLifeEye.class, "LifeEye");
		EntityHandler.registerSnowCreature(EntityIceEye.class, "IceEye");
		EntityHandler.registerMountainMonster(EntityTroll.class, "Troll");
		EntityHandler.registerEntity(EntityIceSpell.class, "IceSpell");
		EntityHandler.registerEntity(EntityFireSpell.class, "FireSpell");
		EntityHandler.registerEntity(EntityDarkPulse.class, "DarkPulse");
		EntityHandler.registerJungleCreature(EntityMonkey.class, "Monkey");
		EntityHandler.registerUnspawnableMob(EntityEmpire.class, "Empire");
		EntityHandler.registerUnspawnableMob(EntityChow.class, "Chow");
		EntityHandler.registerUnspawnableMob(EntityDreadLord.class, "DreadLord");
		EntityHandler.registerUnspawnableMob(EntitySoar.class, "Soar");
		EntityHandler.registerUnspawnableMob(EntityFireDragon.class, "FireDragon");
		EntityHandler.registerEntity(EntityExplosiveSpell.class, "ExplosiveSpell");
		EntityHandler.registerEntity(EntityLaser.class, "Laser");
		EntityHandler.registerNetherMob(EntityFireEye.class, "FireEye");
		EntityHandler.registerNetherMob(EntityLavaShark.class, "LavaShark");
	}
}
