package knokko.rpg.entity.main;

import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityList.EntityEggInfo;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import knokko.rpg.main.KnokkoRPG;
import cpw.mods.fml.common.registry.EntityRegistry;

public class EntityHandler {
	public static void registerEntity(Class entityClass, String name){
		int entityId = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(entityClass, name, entityId);
		EntityRegistry.registerModEntity(entityClass, name, entityId, KnokkoRPG.modInstance, 64, 1, true);
	}
	public static void registerCreatures(Class entityClass, String name){
		int entityId = EntityRegistry.findGlobalUniqueEntityId();
		EntityList.entityEggs.put(Integer.valueOf(entityId), new EntityList.EntityEggInfo(entityId, 494999444, 333333333));
		EntityRegistry.addSpawn(entityClass, 25, 3, 5, EnumCreatureType.creature, BiomeGenBase.plains, BiomeGenBase.savanna, BiomeGenBase.savannaPlateau);
		EntityRegistry.registerGlobalEntityID(entityClass, name, entityId);
		EntityRegistry.registerModEntity(entityClass, name, entityId, KnokkoRPG.modInstance, 64, 1, true);
	}
	public static void registerSnowCreature(Class entityClass, String name){
		int entityId = EntityRegistry.findGlobalUniqueEntityId();
		EntityList.entityEggs.put(Integer.valueOf(entityId), new EntityList.EntityEggInfo(entityId, 494999444, 333333333));
		EntityRegistry.addSpawn(entityClass, 25, 3, 5, EnumCreatureType.creature, BiomeGenBase.icePlains, BiomeGenBase.coldTaiga, BiomeGenBase.coldTaigaHills);
		EntityRegistry.registerGlobalEntityID(entityClass, name, entityId);
		EntityRegistry.registerModEntity(entityClass, name, entityId, KnokkoRPG.modInstance, 64, 1, true);
	}
	public static void registerMountainMonster(Class entityClass, String name){
		int entityId = EntityRegistry.findGlobalUniqueEntityId();
		EntityList.entityEggs.put(Integer.valueOf(entityId), new EntityList.EntityEggInfo(entityId, 494999444, 333333333));
		EntityRegistry.addSpawn(entityClass, 25, 3, 5, EnumCreatureType.monster, BiomeGenBase.extremeHills, BiomeGenBase.extremeHillsEdge, BiomeGenBase.extremeHillsPlus);
		EntityRegistry.registerGlobalEntityID(entityClass, name, entityId);
		EntityRegistry.registerModEntity(entityClass, name, entityId, KnokkoRPG.modInstance, 64, 1, true);
	}
	public static void registerJungleCreature(Class entityClass, String name){
		int entityId = EntityRegistry.findGlobalUniqueEntityId();
		EntityList.entityEggs.put(Integer.valueOf(entityId), new EntityList.EntityEggInfo(entityId, 111999111, 111333333));
		EntityRegistry.addSpawn(entityClass, 25, 3, 5, EnumCreatureType.creature, BiomeGenBase.jungle, BiomeGenBase.jungleEdge, BiomeGenBase.jungleHills);
		EntityRegistry.registerGlobalEntityID(entityClass, name, entityId);
		EntityRegistry.registerModEntity(entityClass, name, entityId, KnokkoRPG.modInstance, 64, 1, true);
	}
	public static void registerUnspawnableMob(Class entityClass, String name){
		int entityId = EntityRegistry.findGlobalUniqueEntityId();
		EntityList.entityEggs.put(Integer.valueOf(entityId), new EntityList.EntityEggInfo(entityId, 111999111, 111333333));
		EntityRegistry.registerGlobalEntityID(entityClass, name, entityId);
		EntityRegistry.registerModEntity(entityClass, name, entityId, KnokkoRPG.modInstance, 64, 1, true);
	}
	public static void registerNetherMob(Class entityClass, String name){
		int entityId = EntityRegistry.findGlobalUniqueEntityId();
		EntityList.entityEggs.put(Integer.valueOf(entityId), new EntityList.EntityEggInfo(entityId, 111999111, 111333333));
		EntityRegistry.addSpawn(entityClass, 25, 3, 5, EnumCreatureType.creature, BiomeGenBase.hell);
		EntityRegistry.registerGlobalEntityID(entityClass, name, entityId);
		EntityRegistry.registerModEntity(entityClass, name, entityId, KnokkoRPG.modInstance, 64, 1, true);
	}
}
