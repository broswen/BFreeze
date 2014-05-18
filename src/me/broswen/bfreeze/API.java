package me.broswen.bfreeze;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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
			if(BFreeze.players.contains(getUUID(p))){
				p.sendMessage(ChatColor.AQUA + "[" + ChatColor.DARK_AQUA + "BFreeze" + ChatColor.AQUA + "] " + ChatColor.RESET + message);
			}
		}
	}
	
	//returns if a player is frozen
	public static boolean isFrozen(Player player){
		if(BFreeze.frozen.contains(getUUID(player))){
			return true;
		}
		return false;
	}
	
	//returns if a player is playing
	
	public static boolean isPlaying(Player player){
		if(BFreeze.players.contains(getUUID(player))){
			return true;
		}
		return false;
	}
	
	//returns if a player is unfrozen
	public static boolean isUnfrozen(Player player){
		if(BFreeze.unfrozen.contains(getUUID(player))){
			return true;
		}
		return false;
	}
	
	//returns if a player is a tagger
	public static boolean isTagger(Player player){
		if(BFreeze.taggers.contains(getUUID(player))){
			return true;
		}
		return false;
	}
	
	//returns if a player is on ppcooldown
	public static boolean isPPCooldown(Player player){
		if(BFreeze.ppCooldown.contains(getUUID(player))){
			return true;
		}
		return false;	
	}
	
	//adds a player to ppcooldown
	public static void setPPCooldown(Player player){
		BFreeze.ppCooldown.add(getUUID(player));
	}
	
	public static void removePPCooldown(Player player){
		if(BFreeze.ppCooldown.contains(getUUID(player))){
			BFreeze.ppCooldown.remove(getUUID(player));
		}
	}
	
	//sets a player to "frozen"
	public static void setFrozen(Player player){
		BFreeze.frozen.add(getUUID(player));
	}
	
	public static void removeFrozen(Player player){
		if(BFreeze.frozen.contains(getUUID(player))){
			BFreeze.frozen.remove(getUUID(player));
		}
	}
	
	//sets a player to "unfrozen"
	public static void setUnfrozen(Player player){
		BFreeze.unfrozen.add(getUUID(player));
	}
	
	public static void removeUnfrozen(Player player){
		if(BFreeze.unfrozen.contains(getUUID(player))){
			BFreeze.unfrozen.remove(getUUID(player));
		}
	}
	
	//sets a player to "players"
	public static void setPlaying(Player player){
		BFreeze.players.add(getUUID(player));
	}
	
	public static void removePlaying(Player player){
		if(BFreeze.players.contains(getUUID(player))){
			BFreeze.players.remove(getUUID(player));
		}
	}
	
	//sets a player to "taggers"
	public static void setTagging(Player player){
		BFreeze.taggers.add(getUUID(player));
	}
	
	public static void removeTagging(Player player){
		if(BFreeze.taggers.contains(getUUID(player))){
			BFreeze.taggers.remove(getUUID(player));
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
	
	public static void giveTaggerArmor(Player player){
		player.getInventory().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
		player.getInventory().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
		player.getInventory().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
		player.getInventory().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
	}
}
