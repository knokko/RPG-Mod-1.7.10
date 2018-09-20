package knokko.rpg.data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import knokko.rpg.main.KnokkoRPG;
import knokko.rpg.main.s;
import knokko.rpg.packet.NBTMessage;
import knokko.util.ExtraUtils;
import knokko.util.Position;
import net.minecraft.command.PlayerSelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;

public class WorldData extends WorldSavedData{
	public static final String id = s.i;
	public static final String xp = "experience";
	public static final String racexp = "race xp";
	public static final String team = " team";
	public static final String invite = " is invited for ";
	
	public NBTTagCompound entities;
	public NBTTagCompound teams;
	
	public List undeadTeams = new ArrayList();
	public List eyeTeams = new ArrayList();
	public List monkeyTeams = new ArrayList();
	
	public WorldData(String id) {
		super(id);
	}
	public WorldData(){
		super(id);
	}
	
	public static NBTTagCompound nbt;

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		entities = nbt.getCompoundTag("players");
		teams = nbt.getCompoundTag("teams");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		if(entities == null){
			entities = new NBTTagCompound();
		}
		if(teams == null){
			teams = new NBTTagCompound();
		}
		nbt.setTag("players", entities);
		nbt.setTag("teams", teams);
	}
	public static WorldData get(World world){
		WorldData data = (WorldData) world.loadItemData(WorldData.class, id);
		if(data == null){
			data = new WorldData();
			world.setItemData(id, data);
		}
		return data;
	}
	public static int getXP(EntityPlayer player){
		return getXP(player, getString(player, "class", "hunter"));
	}
	public static int getXP(EntityPlayer player, String Class){
		return getInteger(player, xp + Class);
	}
	public static void addXP(EntityPlayer player, int amount){
		if(!player.worldObj.getGameRules().getGameRuleBooleanValue("rpgXpBlock"))
			addToInteger(player, xp + getString(player, "class", "hunter"), amount);
	}
	public static void setXP(EntityPlayer player, int amount){
		setInteger(player, amount, xp + getString(player, "class", "hunter"));
	}
	public static void addRaceXp(EntityPlayer player, int amount){
		if(!player.worldObj.getGameRules().getGameRuleBooleanValue("rpgXpBlock"))
			addToInteger(player, racexp + getString(player, "race", "human"), amount);
	}
	public static void setRaceXP(EntityPlayer player, int amount){
		setInteger(player, amount, racexp + getString(player, "race", "human"));
	}
	public static int getRaceXP(EntityPlayer player){
		return getInteger(player, racexp + getString(player, "race", "human"));
	}
	public static void setBooleanOption(Entity entity, String option, boolean value){
		if(entity != null){
			WorldData data = get(entity.worldObj);
			if(data.entities == null){
				data.entities = new NBTTagCompound();
			}
			NBTTagCompound nbt = data.entities.getCompoundTag(entity.getUniqueID().toString());
			nbt.setBoolean(option, value);
			data.entities.setTag(entity.getUniqueID().toString(), nbt);
			data.markDirty();
		}
	}
	public static boolean getBooleanOption(Entity entity, String option, boolean noKeyStored){
		if(entity != null){
			WorldData data = get(entity.worldObj);
			if(data.entities == null){
				data.entities = new NBTTagCompound();
			}
			NBTTagCompound nbt = data.entities.getCompoundTag(entity.getUniqueID().toString());
			if(nbt.hasKey(option)){
				return nbt.getBoolean(option);
			}
			else {
				return noKeyStored;
			}
		}
		else {
			return false;
		}
	}
	public static List getOnlinePlayersInTeam(Entity player){
		World world = player.worldObj;
		WorldData data = get(world);
		List players = new ArrayList();
		if(data.entities == null){
			data.entities = new NBTTagCompound();
		}
		if(data.teams == null){
			data.teams = new NBTTagCompound();
		}
		String team = data.entities.getCompoundTag(player.getUniqueID().toString()).getString("team");
		if(team.isEmpty()){
			players.add(player);
		}
		else {
			List allPlayers = world.playerEntities;
			int times = 0;
			while(times < allPlayers.size()){
				EntityPlayer player2 = (EntityPlayer) allPlayers.get(times);
				String id = player2.getUniqueID().toString();
				String team2 = data.entities.getCompoundTag(id).getString("team");
				if(team2.matches(team)){
					players.add(player2);
				}
				++times;
			}
		}
		return players;
	}
	public static boolean isOnSameTeam(Entity entity1, Entity entity2){
		if(entity1 != null && entity2 != null){
			WorldData data = get(entity1.worldObj);
			if(data.entities == null){
				data.entities = new NBTTagCompound();
			}
			NBTTagCompound nbt1 = data.entities.getCompoundTag(entity1.getUniqueID().toString());
			NBTTagCompound nbt2 = data.entities.getCompoundTag(entity2.getUniqueID().toString());
			return nbt.getString("team").matches(nbt2.getString("team"))&& !nbt.getString(team).isEmpty();
		}
		else {
			return false;
		}
	}
	public static boolean createTeam(String name, EntityPlayer player){
		if(name != null && player != null){
			if(!name.isEmpty()){
				WorldData data = get(player.worldObj);
				if(data.teams == null){
					data.teams = new NBTTagCompound();
				}
				if(data.entities == null){
					data.entities = new NBTTagCompound();
				}
				if(data.teams.hasKey(name)){
					if(data.teams.getCompoundTag(name).getCompoundTag("leaders").hasNoTags()){
						data.teams.removeTag(name);
						data.markDirty();
					}
					return false;
				}
				else {
					NBTTagCompound nbt = new NBTTagCompound();
					NBTTagCompound leaders = new NBTTagCompound();
					NBTTagCompound nbt3 = data.entities.getCompoundTag(player.getUniqueID().toString());
					leaders.setBoolean(player.getUniqueID().toString(), true);
					nbt3.setString("team", name);
					nbt.setTag("leaders", leaders);
					data.teams.setTag(name, nbt);
					data.markDirty();
					return true;
				}
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
	public static boolean isLeader(String teamName, EntityPlayer player){
		if(teamName != null && player != null){
			if(!teamName.isEmpty()){
				WorldData data = get(player.worldObj);
				if(data.teams == null){
					data.teams = new NBTTagCompound();
				}
				return data.teams.getCompoundTag(teamName).getCompoundTag("leaders").getBoolean(player.getUniqueID().toString());
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
	public static void promotePlayerInTeam(String teamName, EntityPlayer player){
		if(teamName != null && player != null){
			if(!teamName.isEmpty()){
				WorldData data = get(player.worldObj);
				if(data.teams == null){
					data.teams = new NBTTagCompound();
				}
				NBTTagCompound leaders = data.teams.getCompoundTag(teamName).getCompoundTag("leaders");
				leaders.setBoolean(player.getUniqueID().toString(), true);
				data.teams.getCompoundTag(teamName).setTag("leaders", leaders);
				data.markDirty();
			}
		}
	}
	public static void setString(EntityPlayer player, String value, String type){
		if(player != null && value != null){
				WorldData data = get(player.worldObj);
				if(data.entities == null){
					data.entities = new NBTTagCompound();
				}
				NBTTagCompound nbt = data.entities.getCompoundTag(player.getUniqueID().toString());
				nbt.setString(type, value);
				data.markDirty();
		}
	}
	public static boolean isChatType(String string){
		return string.matches("public") || string.matches("team");
	}
	public static String getString(Entity player, String type, String noKeyStored){
		if(player != null){
			WorldData data = get(player.worldObj);
			if(data.entities == null){
				data.entities = new NBTTagCompound();
			}
			NBTTagCompound nbt = data.entities.getCompoundTag(player.getUniqueID().toString());
			if(nbt.hasKey(type)){
				return nbt.getString(type);
			}
			else {
				return noKeyStored;
			}
		}
		else {
			return null;
		}
	}
	public static void setInteger(Entity entity, int tickTime, String type){
		if(entity != null && tickTime >= 0){
			WorldData data = get(entity.worldObj);
			if(data.entities == null){
				data.entities = new NBTTagCompound();
			}
			NBTTagCompound nbt = data.entities.getCompoundTag(entity.getUniqueID().toString());
			nbt.setInteger(type, tickTime);
			data.entities.setTag(entity.getUniqueID().toString(), nbt);
			data.markDirty();
		}
	}
	public static boolean isIntegerPositive(Entity entity, String type){
		if(entity != null){
			WorldData data = get(entity.worldObj);
			if(data.entities == null){
				data.entities = new NBTTagCompound();
			}
			NBTTagCompound nbt = data.entities.getCompoundTag(entity.getUniqueID().toString());
			return nbt.getInteger(type) > 0;
		}
		else {
			return false;
		}
	}
	public static int getInteger(Entity entity, String type){
		if(entity != null){
			WorldData data = get(entity.worldObj);
			if(data.entities == null){
				data.entities = new NBTTagCompound();
			}
			NBTTagCompound nbt = data.entities.getCompoundTag(entity.getUniqueID().toString());
			return nbt.getInteger(type);
		}
		else {
			return 0;
		}
	}
	public static void addToInteger(Entity entity, String type, int value){
		if(type.contains("xp") && entity.worldObj.getGameRules().getGameRuleBooleanValue("rpgXpBlock"))
			return;
		if(entity != null){
			WorldData data = get(entity.worldObj);
			if(data.entities == null){
				data.entities = new NBTTagCompound();
			}
			NBTTagCompound nbt = data.entities.getCompoundTag(entity.getUniqueID().toString());
			int old = nbt.getInteger(type);
			nbt.setInteger(type, old + value);
			data.entities.setTag(entity.getUniqueID().toString(), nbt);
			data.markDirty();
		}
	}
	public static void addToInteger(Entity entity, String type, int value, int max){
		if(entity != null){
			WorldData data = get(entity.worldObj);
			if(data.entities == null){
				data.entities = new NBTTagCompound();
			}
			NBTTagCompound nbt = data.entities.getCompoundTag(entity.getUniqueID().toString());
			int old = nbt.getInteger(type);
			if(old + value <= max){
				nbt.setInteger(type, old + value);
			}
			else {
				nbt.setInteger(type, max);
			}
			data.entities.setTag(entity.getUniqueID().toString(), nbt);
			data.markDirty();
		}
	}
	public static void removeFromInteger(Entity entity, String type, int value, int min){
		if(entity != null){
			WorldData data = get(entity.worldObj);
			if(data.entities == null){
				data.entities = new NBTTagCompound();
			}
			NBTTagCompound nbt = data.entities.getCompoundTag(entity.getUniqueID().toString());
			int old = nbt.getInteger(type);
			if(old - value >= min){
				nbt.setInteger(type, old - value);
			}
			else {
				nbt.setInteger(type, min);
			}
			data.entities.setTag(entity.getUniqueID().toString(), nbt);
			data.markDirty();
		}
	}
	public static void updateTimers(Entity entity){
		if(entity != null){
			WorldData data = get(entity.worldObj);
			if(data.entities == null){
				data.entities = new NBTTagCompound();
			}
			NBTTagCompound nbt = data.entities.getCompoundTag(entity.getUniqueID().toString());
			updateInteger(nbt, "windpunch");
			updateInteger(nbt, "firepunch");
			updateInteger(nbt, "icepunch");
			updateInteger(nbt, "paralyzed");
			updateInteger(nbt, "poisonblade");
			updateInteger(nbt, "cooldown");
			updateInteger(nbt, "special cooldown");
			data.markDirty();
		}
	}
	
	public static void removeTimer(Entity entity, String type){
		if(entity != null){
			WorldData data = get(entity.worldObj);
			if(data.entities != null){
				NBTTagCompound nbt = data.entities.getCompoundTag(entity.getUniqueID().toString());
				nbt.removeTag(type);
				data.entities.setTag(entity.getUniqueID().toString(), nbt);
				data.markDirty();
			}
		}
	}
	private static void updateInteger(NBTTagCompound nbt, String type){
		if(nbt.hasKey(type)){
			nbt.setInteger(type, nbt.getInteger(type) - 1);
			if(nbt.getInteger(type) <= 0){
				nbt.removeTag(type);
			}
		}
	}
	public static void updateClient(EntityPlayer player){
		if(player instanceof EntityPlayerMP){
			WorldData data = get(player.worldObj);
			if(data.entities != null){
				if(data.entities.hasKey(player.getUniqueID().toString())){
					KnokkoRPG.network.sendTo(new NBTMessage(data.entities.getCompoundTag(player.getUniqueID().toString()), player), (EntityPlayerMP) player);
				}
			}
		}
	}
	public static void removeTag(Entity entity){
		if(entity != null){
			WorldData data = get(entity.worldObj);
			if(data.entities != null){
				data.entities.removeTag(entity.getUniqueID().toString());
				data.markDirty();
			}
		}
	}
}
