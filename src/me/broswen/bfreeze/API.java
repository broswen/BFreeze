package me.broswen.bfreeze;

import java.util.Random;

import me.broswen.bfreeze.utils.GameManager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;

public class API {

	static ItemStack pocketSand = new ItemStack(Material.SAND);
	static ItemMeta psim = pocketSand.getItemMeta();
	
	//returns a players points in an int
	public static int getPoints(Player player) {
		
		if(BFreeze.points.get(player.getUniqueId().toString()) == null){
			return 0;
		}
		
		return BFreeze.points.get(player.getUniqueId().toString());
	}
	
	//adds points to a players number of points
	public static void addPoints(Player player, int points){
		BFreeze.points.put(getUUID(player), BFreeze.points.get(getUUID(player)) + points);
	}
	
	//removes points from a players number of points
		public static void removePoints(Player player, int points){
			BFreeze.points.put(getUUID(player), BFreeze.points.get(getUUID(player)) - points);
		}
	
	//returns a players UUID as a string
	public static String getUUID(Player player){
		return player.getUniqueId().toString();
	}
	
	//returns a players name as a string
	public static String getName(Player player){
		return player.getName();
	}
	
	//broadcasts a formatted message
	public static void broadcastMessage(String message){
		Bukkit.broadcastMessage(ChatColor.AQUA + "[" + ChatColor.DARK_AQUA + "BFreeze" + ChatColor.AQUA + "] " + ChatColor.RESET + message);
	}
	
	//send formatted message
	public static void sendMessage(Player player, String message){
		player.sendMessage(ChatColor.AQUA + "[" + ChatColor.DARK_AQUA + "BFreeze" + ChatColor.AQUA + "] " + ChatColor.RESET + message);
	}
	
	//broadcasts to game members
	public static void broadcastToPlayers(String message){
		for(Player p : Bukkit.getOnlinePlayers()){
			if(BFreeze.players.contains(getName(p))){
				p.sendMessage(ChatColor.AQUA + "[" + ChatColor.DARK_AQUA + "BFreeze" + ChatColor.AQUA + "] " + ChatColor.RESET + message);
			}
		}
	}
	
	//returns if a player is frozen
	public static boolean isFrozen(Player player){
		if(BFreeze.frozen.contains(getName(player))){
			return true;
		}
		return false;
	}
	
	//returns if a player is playing
	
	public static boolean isPlaying(Player player){
		if(BFreeze.players.contains(getName(player))){
			return true;
		}
		return false;
	}
	
	//returns if a player is unfrozen
	public static boolean isUnfrozen(Player player){
		if(BFreeze.unfrozen.contains(getName(player))){
			return true;
		}
		return false;
	}
	
	//returns if a player is a tagger
	public static boolean isTagger(Player player){
		if(BFreeze.taggers.contains(getName(player))){
			return true;
		}
		return false;
	}
	
	//returns if a player is on ppcooldown
	public static boolean isPPCooldown(Player player){
		if(BFreeze.ppCooldown.contains(getName(player))){
			return true;
		}
		return false;	
	}
	
	//adds a player to ppcooldown
	public static void setPPCooldown(Player player){
		BFreeze.ppCooldown.add(getName(player));
	}
	
	public static void removePPCooldown(Player player){
		if(BFreeze.ppCooldown.contains(getName(player))){
			BFreeze.ppCooldown.remove(getName(player));
		}
	}
	
	//returns if a player is on jbCooldown
		public static boolean isJBCooldown(Player player){
			if(BFreeze.jbCooldown.contains(getName(player))){
				return true;
			}
			return false;	
		}
		
		//adds a player to jbCooldown
		public static void setJBCooldown(Player player){
			BFreeze.jbCooldown.add(getName(player));
		}
		
		public static void removeJBCooldown(Player player){
			if(BFreeze.jbCooldown.contains(getName(player))){
				BFreeze.jbCooldown.remove(getName(player));
			}
		}
	
	//sets a player to "frozen"
	public static void setFrozen(Player player){
		BFreeze.frozen.add(getName(player));
	}
	
	public static void removeFrozen(Player player){
		if(BFreeze.frozen.contains(getName(player))){
			BFreeze.frozen.remove(getName(player));
		}
	}
	
	//sets a player to "unfrozen"
	public static void setUnfrozen(Player player){
		BFreeze.unfrozen.add(getName(player));
	}
	
	public static void removeUnfrozen(Player player){
		if(BFreeze.unfrozen.contains(getName(player))){
			BFreeze.unfrozen.remove(getName(player));
		}
	}
	
	//sets a player to "players"
	public static void setPlaying(Player player){
		BFreeze.players.add(getName(player));
	}
	
	public static void removePlaying(Player player){
		if(BFreeze.players.contains(getName(player))){
			BFreeze.players.remove(getName(player));
		}
	}
	
	//sets a player to "taggers"
	public static void setTagging(Player player){
		BFreeze.taggers.add(getName(player));
	}
	
	public static void removeTagging(Player player){
		if(BFreeze.taggers.contains(getName(player))){
			BFreeze.taggers.remove(getName(player));
		}
	}
	
	//teleports a player to a location
	public static void teleportPlayer(Player player, Location location){
		player.teleport(location);
	}
	
	//resets arraylists
	public static void resetArrayLists(){
		BFreeze.frozen = null;
		BFreeze.taggers = null;
	}

	//freezes a player
	public static void freezePlayer(Player player) {
		psim.setDisplayName(ChatColor.GOLD + "Pocket Sand");
		pocketSand.setItemMeta(psim);
		
		BFreeze.totalFrozen++;
		BFreeze.totalUnfrozen--;
		API.removeUnfrozen(player);
		API.setFrozen(player);
		player.getInventory().setHelmet(new ItemStack(Material.PACKED_ICE));
		if(!player.getInventory().contains(pocketSand)){
			API.givePocketSand(player);
		}
		player.getWorld().playEffect(player.getLocation().add(0, 1, 0), Effect.STEP_SOUND, 174);
        //player.getWorld().playSound(player.getLocation(), Sound.CREEPER_DEATH, 2, 3); 
	}

	//unfreezes a player
	public static void unfreeze(Player player) {
		BFreeze.totalFrozen--;
		BFreeze.totalUnfrozen++;
		API.removeFrozen(player);
		API.setUnfrozen(player);
		player.getInventory().setHelmet(new ItemStack(Material.AIR));
		player.getWorld().playEffect(player.getLocation().add(0, 1, 0), Effect.STEP_SOUND, 174);
		//player.getWorld().playSound(player.getLocation(), Sound.CHICKEN_EGG_POP, 2, -2);
	}
	
	//resets various player stuff
	public static void resetPlayer(Player player){
		player.setGameMode(GameMode.SURVIVAL);
		player.setFoodLevel(20);
		player.getInventory().clear();
		player.getInventory().setArmorContents(null);
	}
	
	//gives the boost potion
	public static void givePowerPotion(Player player){
		ItemStack powerPot = new ItemStack(Material.POTION);
		ItemMeta ppim = powerPot.getItemMeta();
		ppim.setDisplayName(ChatColor.AQUA + "Power Potion");
		powerPot.setItemMeta(ppim);
		
		player.getInventory().addItem(powerPot);
		
	}
	
	public static void givePocketSand(Player player){
		ItemStack pocketSand = new ItemStack(Material.SAND);
		ItemMeta psim = pocketSand.getItemMeta();
		psim.setDisplayName(ChatColor.GOLD + "Pocket Sand");
		pocketSand.setItemMeta(psim);
		
		player.getInventory().addItem(pocketSand);
	}
	
	public static void giveInvisBottle(Player player){
		ItemStack invisBottle = new ItemStack(Material.GLASS_BOTTLE);
		ItemMeta ibim = invisBottle.getItemMeta();
		ibim.setDisplayName(ChatColor.AQUA + "Invisibility Liquid");
		invisBottle.setItemMeta(ibim);
		
		player.getInventory().addItem(invisBottle);
	}
	
	public static void giveJumpBoots(Player player){
		ItemStack jumpBoots = new ItemStack(Material.IRON_BOOTS);
		ItemMeta jbim = jumpBoots.getItemMeta();
		jbim.setDisplayName(ChatColor.RED + "Air Jordans");
		jumpBoots.setItemMeta(jbim);
		
		player.getInventory().addItem(jumpBoots);
	}
	
	public static void giveTaggerArmor(Player player){
		player.getInventory().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
		player.getInventory().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
		player.getInventory().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
		player.getInventory().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
	}
	
	//returns if everyone is frozen
	public static boolean hasTaggerWon(){
		if(BFreeze.totalUnfrozen <= 0){
			return true;
		}
		return false;
	}
	
	//displays that the tagger won
	public static void taggerWin(Player player){
		 API.broadcastToPlayers(player.getName() + " Has Frozen Everyone!");
		 GameManager.endGame();
	}
	
	public static Player getPlayerPlaying(int i){
		Player player = Bukkit.getServer().getPlayer(BFreeze.players.get(i));
		
		return player;
	}
	
	public static void resetPotions(Player player){
		for (PotionEffect effect : player.getActivePotionEffects()){
			player.removePotionEffect(effect.getType());
		}
	}
	
	public static String getRandomPlayer(){
		int random = new Random().nextInt(BFreeze.players.size());
		String name = BFreeze.players.get(random);
		
		return name;
	}
	
	public static void chooseRandomTagger(){
		Player player = Bukkit.getPlayer(getRandomPlayer());
		
		if(player == null){
			return;
		}
		
		while(BFreeze.lastTagger == player.getName()){
			player = Bukkit.getPlayer(getRandomPlayer());
		}
		
		API.setTagging(player);
		API.removeUnfrozen(player);
		API.resetPotions(player);
		BFreeze.totalUnfrozen--;
		BFreeze.totalTagging++;
		API.broadcastToPlayers(player.getName() + " Is Now The Tagger! RUN!!!");
		BFreeze.lastTagger = API.getName(player);
	}
}
