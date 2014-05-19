package me.broswen.bfreeze;

import java.util.ArrayList;
import java.util.HashMap;

import me.broswen.bfreeze.commands.FreezeCommand;
import me.broswen.bfreeze.events.BlockBreak;
import me.broswen.bfreeze.events.BlockPlace;
import me.broswen.bfreeze.events.EntityDamage;
import me.broswen.bfreeze.events.EntityDamageByEntity;
import me.broswen.bfreeze.events.HungerChange;
import me.broswen.bfreeze.events.InventoryClick;
import me.broswen.bfreeze.events.OnJoin;
import me.broswen.bfreeze.events.PlayerDropItem;
import me.broswen.bfreeze.events.PlayerInteract;
import me.broswen.bfreeze.events.PlayerMove;
import me.broswen.bfreeze.events.PlayerPickupItem;
import me.broswen.bfreeze.events.PlayerQuit;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


public class BFreeze extends JavaPlugin{
	public static BFreeze plugin;
	
	public static String lastTagger;
	
	public static Location playerSpawn, taggerSpawn, lobbySpawn;
	
	public static Boolean gameStarted, gameEnded, taggersStarted;
	
	public static FileConfiguration config;
	
	public static int totalUnfrozen, totalFrozen, totalPlaying, totalTagging;
	
	public static ArrayList<String> players = new ArrayList<>();
	public static ArrayList<String> unfrozen = new ArrayList<>();
	public static ArrayList<String> frozen = new ArrayList<>();
	public static ArrayList<String> taggers = new ArrayList<>();
	public static ArrayList<String> ppCooldown = new ArrayList<>();
	public static ArrayList<String> jbCooldown = new ArrayList<>();
	public static HashMap<String, Integer> points = new HashMap<>();
	
	public void onEnable(){
		this.plugin = this;
		
		loadConfig();
		
		PluginManager pm = Bukkit.getPluginManager();
		
		pm.registerEvents(new EntityDamage(), this);
		pm.registerEvents(new EntityDamageByEntity(), this);
		pm.registerEvents(new PlayerDropItem(), this);
		pm.registerEvents(new PlayerPickupItem(), this);
		pm.registerEvents(new PlayerQuit(), this);
		pm.registerEvents(new BlockBreak(), this);
		pm.registerEvents(new BlockPlace(), this);
		pm.registerEvents(new InventoryClick(), this);
		pm.registerEvents(new PlayerMove(), this);
		pm.registerEvents(new HungerChange(), this);
		pm.registerEvents(new PlayerInteract(), this);
		pm.registerEvents(new OnJoin(), this);
		
		this.getCommand("freeze").setExecutor(new FreezeCommand(this));
		
		playerSpawn = new Location(Bukkit.getWorld(getConfig().getString("playerspawn.world")), getConfig().getDouble("playerspawn.X"), getConfig().getDouble("playerspawn.Y"), getConfig().getDouble("playerspawn.Z"));
		taggerSpawn = new Location(Bukkit.getWorld(getConfig().getString("taggerspawn.world")), getConfig().getDouble("taggerspawn.X"), getConfig().getDouble("taggerspawn.Y"), getConfig().getDouble("taggerspawn.Z"));
		lobbySpawn = new Location(Bukkit.getWorld(getConfig().getString("lobbyspawn.world")), getConfig().getDouble("lobbyspawn.X"), getConfig().getDouble("lobbyspawn.Y"), getConfig().getDouble("lobbyspawn.Z"));
		
		config = this.getConfig();
		
		gameStarted = false;
		gameEnded = false;
		taggersStarted = false;
		totalUnfrozen = 0;
		totalFrozen = 0;
		totalPlaying = 0;
		totalTagging = 0;
		lastTagger = "";
	}
	
	public void onDisable(){
		
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		return true;
	}
	
	public void loadConfig(){
		saveDefaultConfig();
		getConfig().options().copyDefaults(true);
		getConfig().options().copyHeader(true);
		saveConfig();
	}
}
