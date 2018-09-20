package knokko.rpg;

import java.util.ArrayList;
import java.util.List;

import knokko.rpg.data.WorldData;
import knokko.rpg.items.main.RPGItems;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.client.event.FOVUpdateEvent;

public final class RPG {
	public static final IChatComponent noSpecialClass = new ChatComponentTranslation(EnumChatFormatting.RED + "that is not a special class of your class.");
	
	public static final boolean isBasicClass(String string){
		if(string.matches("warrior"))
			return true;
		else if(string.matches("mage"))
			return true;
		else if(string.matches("archer"))
			return true;
		else if(string.matches("hunter"))
			return true;
		else if(string.matches("brawler"))
			return true;
		else 
			return false;
	}
	
	public static boolean isSpecialClass(String string){
		if(string.matches("tank")){
			return true;
		}
		else if(string.matches("berserker")){
			return true;
		}
		else if(string.matches("magicwarrior")){
			return true;
		}
		else if(string.matches("paladin")){
			return true;
		}
		else if(string.matches("shooter")){
			return true;
		}
		else if(string.matches("magicarcher")){
			return true;
		}
		else if(string.matches("healer")){
			return true;
		}
		else if(string.matches("galactic")){
			return true;
		}
		else if(string.matches("necromancer")){
			return true;
		}
		else {
			return false;
		}
	}
	
	public static boolean canPlayerChooseSpecialClass(EntityPlayer player, String string){
		if(string.matches("tank") || string.matches("berserker") || string.matches("magicwarrior") || string.matches("paladin")){
			if(WorldData.getXP(player, "warrior") >= 10000){
				return true;
			}
			else {
				player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.RED + "You must have 10000 xp for warrior to become this class."));
				return false;
			}
		}
		else if(string.matches("shooter") || string.matches("magicarcher")){
			if(WorldData.getXP(player, "archer") >= 10000){
				return true;
			}
			else {
				player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.RED + "You must have 10000 xp for archer to become this class."));
				return false;
			}
		}
		else if(string.matches("healer") || string.matches("galactic") || string.matches("necromancer")){
			if(WorldData.getXP(player, "mage") >= 10000){
				return true;
			}
			else {
				player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.RED + "You must have 10000 xp for mage to become this class."));
				return false;
			}
		}
		else {
			return false;
		}
	}
	public static double getBasePower(EntityPlayer player){
		String Class = WorldData.getString(player, "class", "hunter");
		String race = WorldData.getString(player, "race", "human");
		double power = 1;
		if(Class.matches("brawler")){
			power += 2;
		}
		if(race.matches("golem")){
			power += 9;
		}
		if(race.matches("bear")){
			power += 5;
		}
		return power;
	}
	public static boolean isKnownRace(String string){
		if(string.matches("human")){
			return true;
		}
		else if(string.matches("orc")){
			return true;
		}
		else if(string.matches("enderman")){
			return true;
		}
		else if(string.matches("golem")){
			return true;
		}
		else if(string.matches("bear")){
			return true;
		}
		else if(string.matches("monkey")){
			return true;
		}
		else if(string.matches("elf")){
			return true;
		}
		else if(string.matches("eyeman")){
			return true;
		}
		else {
			return false;
		}
	}
	public static boolean isKnownSpell(String spell){
		if(spell.matches("ice")){
			return true;
		}
		else if(spell.matches("summonarrow")){
			return true;
		}
		else if(spell.matches("poisonblade")){
			return true;
		}
		else if(spell.matches("explosive")){
			return true;
		}
		else if(spell.matches("lightshield")){
			return true;
		}
		else if(spell.matches("lightning")){
			return true;
		}
		else if(spell.matches("windpunch")){
			return true;
		}
		else if(spell.matches("firepunch")){
			return true;
		}
		else if(spell.matches("icepunch")){
			return true;
		}
		else if(spell.matches("lightningarrow")){
			return true;
		}
		else if(spell.matches("poisonarrow")){
			return true;
		}
		else if(spell.matches("cursedarrow")){
			return true;
		}
		else if(spell.matches("powerattack")){
			return true;
		}
		else if(spell.matches("dog")){
			return true;
		}
		else if(spell.matches("fire")){
			return true;
		}
		else if(spell.matches("weakregen")){
			return true;
		}
		else if(spell.matches("darkpulse")){
			return true;
		}
		else if(spell.matches("shield")){
			return true;
		}
		else if(spell.matches("heavyshield")){
			return true;
		}
		else if(spell.matches("gigabarrier")){
			return true;
		}
		else if(spell.matches("berserk")){
			return true;
		}
		else if(spell.matches("fireslash")){
			return true;
		}
		else if(spell.matches("electricslash")){
			return true;
		}
		else if(spell.matches("blooddrain")){
			return true;
		}
		else if(spell.matches("darkslash")){
			return true;
		}
		else if(spell.matches("heal")){
			return true;
		}
		else if(spell.matches("spiritslash")){
			return true;
		}
		else if(spell.matches("smite")){
			return true;
		}
		else if(spell.matches("powershot")){
			return true;
		}
		else if(spell.matches("spark")){
			return true;
		}
		else if(spell.matches("teamheal")){
			return true;
		}
		else if(spell.matches("helpheal")){
			return true;
		}
		else if(spell.matches("regen")){
			return true;
		}
		else if(spell.matches("teamregen")){
			return true;
		}
		else if(spell.matches("helpregen")){
			return true;
		}
		else if(spell.matches("megabarrier")){
			return true;
		}
		else if(spell.matches("starrain")){
			return true;
		}
		else if(spell.matches("meteor")){
			return true;
		}
		else if(spell.matches("fly")){
			return true;
		}
		else if(spell.matches("thunder")){
			return true;
		}
		else if(spell.matches("undeadcontrol")){
			return true;
		}
		else if(spell.matches("pigmanstrike")){
			return true;
		}
		else if(spell.matches("dreadlord")){
			return true;
		}
		else if(spell.matches("chow")){
			return true;
		}
		else if(spell.matches("soar")){
			return true;
		}
		else {
			return false;
		}
	}
	public static boolean isKnownSpell(String spell, EntityPlayer player){
		String Class = WorldData.getString(player, "class", "hunter");
		if(Class.matches("mage")){
			if(spell.matches("ice")){
				return true;
			}
			else if(spell.matches("explosive")){
				return true;
			}
			else if(spell.matches("lightning")){
				return true;
			}
			else if(spell.matches("fire")){
				return true;
			}
			else if(spell.matches("weakregen")){
				return true;
			}
			else if(spell.matches("darkpulse")){
				return true;
			}
			else if(spell.matches("spark")){
				return true;
			}
			else {
				return false;
			}
		}
		else if(Class.matches("archer")){
			if(spell.matches("summonarrow")){
				return true;
			}
			else if(spell.matches("powershot")){
				return true;
			}
			else if(spell.matches("poisonarrow")){
				return true;
			}
			else {
				return false;
			}
		}
		else if(Class.matches("hunter")){
			if(spell.matches("poisonblade")){
				return true;
			}
			else if(spell.matches("dog")){
				return true;
			}
			else {
				return false;
			}
		}
		else if(Class.matches("warrior")){
			if(spell.matches("lightshield")){
				return true;
			}
			else if(spell.matches("powerattack")){
				return true;
			}
			else {
				return false;
			}
		}
		else if(Class.matches("brawler")){
			if(spell.matches("windpunch")){
				return true;
			}
			else if(spell.matches("firepunch")){
				return true;
			}
			else if(spell.matches("icepunch")){
				return true;
			}
			else {
				return false;
			}
		}
		else if(Class.matches("tank")){
			if(spell.matches("lightshield")){
				return true;
			}
			else if(spell.matches("shield")){
				return true;
			}
			else if(spell.matches("heavyshield")){
				return true;
			}
			else if(spell.matches("megabarrier")){
				return true;
			}
			else if(spell.matches("gigabarrier")){
				return true;
			}
			else {
				return false;
			}
		}
		else if(Class.matches("berserker")){
			if(spell.matches("berserk")){
				return true;
			}
			else if(spell.matches("powerattack")){
				return true;
			}
			else {
				return true;
			}
		}
		else if(Class.matches("magicwarrior")){
			if(spell.matches("powerattack")){
				return true;
			}
			else if(spell.matches("fireslash")){
				return true;
			}
			else if(spell.matches("electricslash")){
				return true;
			}
			else if(spell.matches("blooddrain")){
				return true;
			}
			else if(spell.matches("darkslash")){
				return true;
			}
			else if(spell.matches("spiritslash")){
				return true;
			}
			else {
				return false;
			}
		}
		else if(Class.matches("paladin")){
			if(spell.matches("heal")){
				return true;
			}
			else if(spell.matches("spiritslash")){
				return true;
			}
			else if(spell.matches("smite")){
				return true;
			}
			else {
				return false;
			}
		}
		else if(Class.matches("shooter")){
			if(spell.matches("powershot")){
				return true;
			}
			else if(spell.matches("summonarrow")){
				return true;
			}
			else {
				return false;
			}
		}
		else if(Class.matches("magicarcher")){
			if(spell.matches("summonarrow")){
				return true;
			}
			else if(spell.matches("cursedarrow")){
				return true;
			}
			else if(spell.matches("lightningarrow")){
				return true;
			}
			else if(spell.matches("poisonarrow")){
				return true;
			}
			else if(spell.matches("powershot")){
				return true;
			}
			else {
				return false;
			}
		}
		else if(Class.matches("healer")){
			if(spell.matches("weakregen")){
				return true;
			}
			else if(spell.matches("heal")){
				return true;
			}
			else if(spell.matches("ice")){
				return true;
			}
			else if(spell.matches("fire")){
				return true;
			}
			else if(spell.matches("teamheal")){
				return true;
			}
			else if(spell.matches("helpheal")){
				return true;
			}
			else if(spell.matches("regen")){
				return true;
			}
			else if(spell.matches("teamregen")){
				return true;
			}
			else if(spell.matches("helpregen")){
				return true;
			}
			else {
				return false;
			}
		}
		else if(Class.matches("galactic")){
			if(spell.matches("spark")){
				return true;
			}
			else if(spell.matches("fire")){
				return true;
			}
			else if(spell.matches("lightning")){
				return true;
			}
			else if(spell.matches("explosive")){
				return true;
			}
			else if(spell.matches("meteor")){
				return true;
			}
			else if(spell.matches("starrain")){
				return true;
			}
			else if(spell.matches("fly")){
				return true;
			}
			else if(spell.matches("thunder")){
				return true;
			}
			else {
				return false;
			}
		}
		else if(Class.matches("necromancer")){
			if(spell.matches("darkpulse")){
				return true;
			}
			else if(spell.matches("undeadcontrol")){
				return true;
			}
			else if(spell.matches("pigmanstrike")){
				return true;
			}
			else if(spell.matches("chow")){
				return true;
			}
			else if(spell.matches("dreadlord")){
				return true;
			}
			else if(spell.matches("soar")){
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
	public static String getSpell(int rank, EntityPlayer player){
		String Class = WorldData.getString(player, "class", "hunter");
		if(Class.matches("mage")){
			if(rank <= 1){
				return "ice";
			}
			else if(rank == 2){
				return "explosive";
			}
			else if(rank == 3){
				return "lightning";
			}
			else if(rank == 4){
				return "fire";
			}
			else if(rank == 5){
				return "weakregen";
			}
			else if(rank == 6){
				return "darkpulse";
			}
			else if(rank == 7){
				return "spark";
			}
			else {
				return null;
			}
		}
		else if(Class.matches("archer")){
			if(rank <= 1){
				return "summonArrow";
			}
			else if(rank == 2){
				return "poisonarrow";
			}
			else if(rank == 3){
				return "ligthningarrow";
			}
			else if(rank == 4){
				return "cursedarrow";
			}
			else {
				return null;
			}
		}
		else if(Class.matches("hunter")){
			if(rank <= 1){
				return "poisonblade";
			}
			else if(rank == 2){
				return "dog";
			}
			else {
				return null;
			}
		}
		else if(Class.matches("warrior")){
			if(rank <= 1){
				return "lightshield";
			}
			else if(rank == 2){
				return "powerattack";
			}
			else {
				return null;
			}
		}
		else if(Class.matches("tank")){
			if(rank == 1){
				return "lightshield";
			}
			else if(rank == 2){
				return "shield";
			}
			else if(rank == 3){
				return "heavyshield";
			}
			else if(rank == 4){
				return "megabarrier";
			}
			else if(rank == 5){
				return "gigabarrier";
			}
			else {
				return null;
			}
		}
		else if(Class.matches("berserker")){
			if(rank == 1){
				return "powerattack";
			}
			else if(rank == 2){
				return "berserk";
			}
			else {
				return null;
			}
		}
		else if(Class.matches("magicwarrior")){
			if(rank == 1){
				return "powerattack";
			}
			else if(rank == 2){
				return "fireslash";
			}
			else if(rank == 3){
				return "electricslash";
			}
			else if(rank == 4){
				return "blooddrain";
			}
			else if(rank == 5){
				return "darkslash";
			}
			else if(rank == 6){
				return "spiritslash";
			}
			else {
				return null;
			}
		}
		else if(Class.matches("paladin")){
			if(rank == 1){
				return "heal";
			}
			else if(rank == 2){
				return "spiritslash";
			}
			else if(rank == 3){
				return "smite";
			}
			else {
				return null;
			}
		}
		else if(Class.matches("shooter")){
			if(rank == 1){
				return "powershot";
			}
			else if(rank == 2){
				return "summonarrow";
			}
			else {
				return null;
			}
		}
		else if(Class.matches("magicarcher")){
			if(rank == 1){
				return "summonarrow";
			}
			else if(rank == 2){
				return "poisonarrow";
			}
			else if(rank == 3){
				return "lightningarrow";
			}
			else if(rank == 4){
				return "powershot";
			}
			else if(rank == 5){
				return "cursedarrow";
			}
			else {
				return null;
			}
		}
		else if(Class.matches("healer")){
			if(rank == 1){
				return "heal";
			}
			else if(rank == 2){
				return "weakregen";
			}
			else if(rank == 3){
				return "ice";
			}
			else if(rank == 4){
				return "fire";
			}
			else if(rank == 5){
				return "teamheal";
			}
			else if(rank == 6){
				return "helpheal";
			}
			else if(rank == 7){
				return "regen";
			}
			else if(rank == 8){
				return "teamregen";
			}
			else if(rank == 9){
				return "helpregen";
			}
			else {
				return null;
			}
		}
		else if(Class.matches("galactic")){
			if(rank == 1){
				return "fire";
			}
			else if(rank == 2){
				return "spark";
			}
			else if(rank == 3){
				return "lightning";
			}
			else if(rank == 4){
				return "explosive";
			}
			else if(rank == 5){
				return "meteor";
			}
			else if(rank == 6){
				return "starrain";
			}
			else if(rank == 7){
				return "fly";
			}
			else if(rank == 8){
				return "thunder";
			}
			else {
				return null;
			}
		}
		else if(Class.matches("necromancer")){
			if(rank == 1){
				return "darkpulse";
			}
			else if(rank == 2){
				return "undeadcontrol";
			}
			else if(rank == 3){
				return "pigmanstrike";
			}
			else if(rank == 4){
				return "chow";
			}
			else if(rank == 5){
				return "dreadlord";
			}
			else if(rank == 6){
				return "soar";
			}
			else {
				return null;
			}
		}
		else {
			return null;
		}
	}
	public static int amountSpells(EntityPlayer player){
		String Class = WorldData.getString(player, "class", "hunter");
		if(Class.matches("warrior")){
			return 2;
		}
		else if(Class.matches("mage")){
			return 7;
		}
		else if(Class.matches("hunter")){
			return 2;
		}
		else if(Class.matches("archer")){
			return 3;
		}
		else if(Class.matches("brawler")){
			return 3;
		}
		else if(Class.matches("tank")){
			return 5;
		}
		else if(Class.matches("berserker")){
			return 2;
		}
		else if(Class.matches("magicwarrior")){
			return 6;
		}
		else if(Class.matches("paladin")){
			return 3;
		}
		else if(Class.matches("shooter")){
			return 2;
		}
		else if(Class.matches("magicarcher")){
			return 5;
		}
		else if(Class.matches("healer")){
			return 9;
		}
		else if(Class.matches("galactic")){
			return 8;
		}
		else if(Class.matches("necromancer")){
			return 6;
		}
		else {
			return 0;
		}
	}
	public static boolean isKnownOption(String option){
		if(option.matches("hurtMessages")){
			return true;
		}
		else if(option.matches("manaMessages")){
			return true;
		}
		else if(option.matches("hitMessages")){
			return true;
		}
		else if(option.matches("killMessages")){
			return true;
		}
		else if(option.matches("deathMessages")){
			return true;
		}
		else {
			return false;
		}
	}
	public static List getRaces(){
		List races = new ArrayList();
		races.add("human");
		races.add("orc");
		races.add("enderman");
		races.add("golem");
		races.add("bear");
		races.add("monkey");
		races.add("elf");
		races.add("eyeman");
		return races;
	}
	public static String getRace(int rank){
		return (String) getRaces().get(rank);
	}
	public static List getBaseClasses(){
		List classes = new ArrayList();
		classes.add("warrior");
		classes.add("mage");
		classes.add("archer");
		classes.add("hunter");
		classes.add("brawler");
		return classes;
	}
	public static String getBaseClass(int rank){
		return (String) getBaseClasses().get(rank);
	}
	private static double getClassStrengt(String Class){
		if(Class.matches("warrior")){
			return 1.25;
		}
		else if(Class.matches("tank")){
			return 1;
		}
		else if(Class.matches("mage")){
			return 0.5;
		}
		else if(Class.matches("archer")){
			return 0.5;
		}
		else if(Class.matches("hunter")){
			return 1;
		}
		else if(Class.matches("brawler")){
			return 1;
		}
		else if(Class.matches("berserker")){
			return 2;
		}
		else if(Class.matches("magicwarrior")){
			return 1;
		}
		else if(Class.matches("paladin")){
			return 1;
		}
		else if(Class.matches("shooter")){
			return 0.5;
		}
		else if(Class.matches("magicarcher")){
			return 0.5;
		}
		else if(Class.matches("healer")){
			return 0.5;
		}
		else if(Class.matches("galactic")){
			return 0.5;
		}
		else if(Class.matches("necromancer")){
			return 0.5;
		}
		else {
			return 1;
		}
	}
	private static double getClassStrengt(EntityPlayer player){
		return getClassStrengt(WorldData.getString(player, "class", "hunter"));
	}
	private static double getRaceStrengt(String race){
		if(race.matches("human")){
			return 1;
		}
		else if(race.matches("orc")){
			return 3;
		}
		else if(race.matches("enderman")){
			return 2;
		}
		else if(race.matches("golem")){
			return 1;
		}
		else if(race.matches("bear")){
			return 1.5;
		}
		else if(race.matches("monkey")){
			return 1.2;
		}
		else if(race.matches("elf")){
			return 0.5;
		}
		else if(race.matches("eyeman")){
			return 0.4;
		}
		else {
			return 1;
		}
	}
	
	public static ItemStack getWeapon(EntityPlayer player){
		String Class = WorldData.getString(player, "class", "hunter");
		if(Class.matches("warrior")){
			if(WorldData.getString(player, "race", "human").matches("monkey")){
				return new ItemStack(RPGItems.bananaSword);
			}
			else {
				return new ItemStack(Items.iron_sword);
			}
		}
		else if(Class.matches("berserker")){
			return new ItemStack(Items.iron_axe);
		}
		else if(Class.matches("tank")){
			return new ItemStack(Items.stone_sword);
		}
		else if(Class.matches("magicwarrior")){
			return new ItemStack(Items.golden_sword);
		}
		else if(Class.matches("archer")){
			return new ItemStack(Items.bow);
		}
		else if(Class.matches("brawler")){
			return null;
		}
		else if(Class.matches("hunter")){
			return new ItemStack(Items.bow);
		}
		else if(Class.matches("mage")){
			return new ItemStack(RPGItems.woodWand);
		}
		else if(Class.matches("paladin")){
			ItemStack sword = new ItemStack(Items.iron_sword);
			sword.addEnchantment(Enchantment.smite, 5);
			return sword;
		}
		else if(Class.matches("shooter")){
			ItemStack bow = new ItemStack(Items.bow);
			bow.addEnchantment(Enchantment.power, 3);
			return bow;
		}
		else if(Class.matches("magicarcher")){
			ItemStack bow = new ItemStack(Items.bow);
			bow.addEnchantment(Enchantment.infinity, 1);
			return bow;
		}
		else if(Class.matches("healer")){
			return new ItemStack(RPGItems.woodWand);
		}
		else if(Class.matches("necromancer")){
			return new ItemStack(RPGItems.woodWand);
		}
		else {
			return null;
		}
	}
	private static double getRaceHealth(String race){
		if(race.matches("human")){
			return 1;
		}
		else if(race.matches("orc")){
			return 2.5;
		}
		else if(race.matches("enderman")){
			return 2;
		}
		else if(race.matches("golem")){
			return 5;
		}
		else if(race.matches("bear")){
			return 2;
		}
		else if(race.matches("monkey")){
			return 1.2;
		}
		else if(race.matches("elf")){
			return 0.5;
		}
		else if(race.matches("eyeman")){
			return 0.75;
		}
		else {
			return 1;
		}
	}
	private static double getRaceHealth(EntityPlayer player){
		return getRaceHealth(WorldData.getString(player, "race", "human"));
	}
	public static double getPlayerHealth(EntityPlayer player){
		double xp = WorldData.getRaceXP(player);
		double raceHealth = getRaceHealth(player);
		return raceHealth * (1 + (xp * 0.0001));
	}
	private static double getRaceStrengt(EntityPlayer player){
		return getRaceStrengt(WorldData.getString(player, "race", "human"));
	}
	public static double getPlayerStrengt(EntityPlayer player){
		double raceStrengt = getRaceStrengt(player);
		double classStrengt = getClassStrengt(player);
		double xp = WorldData.getXP(player);
		double racexp = WorldData.getXP(player);
		return raceStrengt * (1 + (xp * 0.0001) + (0.0001 * racexp)) * classStrengt;
	}
	private static double getRaceMana(String race){
		if(race.matches("human")){
			return 1;
		}
		else if(race.matches("enderman")){
			return 0.75;
		}
		else if(race.matches("orc")){
			return 0.25;
		}
		else if(race.matches("golem")){
			return 0.25;
		}
		else if(race.matches("bear")){
			return 0.2;
		}
		else if(race.matches("monkey")){
			return 0.75;
		}
		else if(race.matches("elf")){
			return 2;
		}
		else if(race.matches("eyeman")){
			return 3;
		}
		else {
			return 1;
		}
	}
	private static double getClassMana(String Class){
		if(Class.matches("warrior")){
			return 0.75;
		}
		else if(Class.matches("tank")){
			return 1.5;
		}
		else if(Class.matches("mage")){
			return 2;
		}
		else if(Class.matches("archer")){
			return 0.75;
		}
		else if(Class.matches("hunter")){
			return 1;
		}
		else if(Class.matches("brawler")){
			return 0.75;
		}
		else if(Class.matches("berserker")){
			return 0.5;
		}
		else if(Class.matches("magicwarrior")){
			return 2;
		}
		else if(Class.matches("paladin")){
			return 2;
		}
		else if(Class.matches("shooter")){
			return 0.5;
		}
		else if(Class.matches("magicarcher")){
			return 2;
		}
		else if(Class.matches("healer")){
			return 2.5;
		}
		else if(Class.matches("galactic")){
			return 2;
		}
		else if(Class.matches("necromancer")){
			return 3;
		}
		else {
			return 1;
		}
	}
	private static double getClassMana(EntityPlayer player){
		return getClassMana(WorldData.getString(player, "class", "hunter"));
	}
	private static double getRaceMana(EntityPlayer player){
		return getRaceMana(WorldData.getString(player, "race", "human"));
	}
	public static double getPlayerMana(EntityPlayer player){
		double raceMana = getRaceMana(player);
		double classMana = getClassMana(player);
		double xp = WorldData.getXP(player);
		double racexp = WorldData.getRaceXP(player);
		return raceMana * (1 + (racexp * 0.0001) + (xp * 0.0001)) * classMana;
	}
	public static double getArcherPower(EntityPlayer player){
		double classPower = 0.5;
		double xp = 0;
		if(WorldData.getString(player, "class", "hunter").matches("archer")){
			xp = WorldData.getXP(player);
			classPower = 2;
		}
		else if(WorldData.getString(player, "class", "hunter").matches("hunter")){
			xp = WorldData.getXP(player);
			classPower = 1;
		}
		else if(WorldData.getString(player, "class", "hunter").matches("shooter")){
			xp = WorldData.getXP(player);
			classPower = 3;
		}
		else if(WorldData.getString(player, "class", "hunter").matches("magicarcher")){
			xp = WorldData.getXP(player);
			classPower = 1.5;
		}
		return classPower * (1 + (xp * 0.0001));
	}
	private static double getRaceSpeed(EntityPlayer player){
		String race = WorldData.getString(player, "race", "human");
		if(race.matches("orc")){
			return 0.6;
		}
		else if(race.matches("enderman")){
			return 0.75;
		}
		else if(race.matches("human")){
			return 1;
		}
		else if(race.matches("golem")){
			return 0.5;
		}
		else if(race.matches("bear")){
			return 2;
		}
		else if(race.matches("monkey")){
			return 1.7;
		}
		else if(race.matches("elf")){
			return 1.3;
		}
		else if(race.matches("eyeman")){
			return 1;
		}
		else {
			return 1;
		}
	}
	private static double getClassSpeed(EntityPlayer player){
		String s = WorldData.getString(player, "class", "hunter");
		if(s.matches("warrior")){
			return 0.7;
		}
		else if(s.matches("tank")){
			return 0.6;
		}
		else if(s.matches("archer")){
			return 0.9;
		}
		else if(s.matches("berserker")){
			return 1.5;
		}
		else if(s.matches("magicwarrior")){
			return 0.8;
		}
		else if(s.matches("paladin")){
			return 0.8;
		}
		else if(s.matches("magicarcher")){
			return 0.9;
		}
		else {
			return 1;
		}
	}
	public static double getPlayerSpeed(EntityPlayer player){
		return getClassSpeed(player) * getRaceSpeed(player);
	}
	private static double getRaceSpirit(EntityPlayer player){
		String s = WorldData.getString(player, "race", "human");
		if(s.matches("orc")){
			return 0.25;
		}
		else if(s.matches("human")){
			return 1;
		}
		else if(s.matches("enderman")){
			return 0.5;
		}
		else if(s.matches("golem")){
			return 0.25;
		}
		else if(s.matches("bear")){
			return 0.2;
		}
		else if(s.matches("monkey")){
			return 0.75;
		}
		else if(s.matches("elf")){
			return 2;
		}
		else if(s.matches("eyeman")){
			return 1;
		}
		else {
			return 1;
		}
	}
	private static double getClassSpirit(EntityPlayer player){
		String s = WorldData.getString(player, "class", "hunter");
		if(s.matches("warrior")){
			return 0.25;
		}
		else if(s.matches("tank")){
			return 0.25;
		}
		else if(s.matches("archer")){
			return 0.25;
		}
		else if(s.matches("mage")){
			return 2;
		}
		else if(s.matches("brawler")){
			return 0.25;
		}
		else if(s.matches("berserker")){
			return 0.25;
		}
		else if(s.matches("paladin")){
			return 1.5;
		}
		else if(s.matches("shooter")){
			return 0.25;
		}
		else if(s.matches("healer")){
			return 2;
		}
		else if(s.matches("galactic")){
			return 3;
		}
		else if(s.matches("necromancer")){
			return 2;
		}
		else {
			return 1;
		}
	}
	public static double getPlayerSpirit(EntityPlayer player){
		double raceSpirit = getRaceSpirit(player);
		double classSpirit = getClassSpirit(player);
		double raceXp = WorldData.getRaceXP(player);
		double xp = WorldData.getXP(player);
		return raceSpirit * (1 + (raceXp * 0.0001) + (xp * 0.0001)) * classSpirit;
	}
	public static String getSpecial(EntityPlayer player){
		String special = "";
		String race = WorldData.getString(player, "race", "human");
		if(race.matches("enderman")){
			special = "teleport";
		}
		else if(race.matches("human")){
			special = "white gift";
		}
		else if(race.matches("orc")){
			special = "rage";
		}
		else if(race.matches("golem")){
			special = "iron bash";
		}
		else if(race.matches("bear")){
			special = "agility boost";
		}
		else if(race.matches("monkey")){
			special = "agility boost";
		}
		else if(race.matches("elf")){
			special = "white gift";
		}
		else if(race.matches("eyeman")){
			special = "zoom";
		}
		return special;
	}
}
