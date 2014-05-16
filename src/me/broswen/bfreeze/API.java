package me.broswen.bfreeze;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class API {

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
				Bukkit.broadcastMessage(ChatColor.AQUA + "[" + ChatColor.DARK_AQUA + "BFreeze" + ChatColor.AQUA + "] " + ChatColor.RESET + message);
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
	
	//sets a player to "frozen"
	public static void setFrozen(Player player){
		BFreeze.frozen.add(getUUID(player));
	}
	
	public static void removeFrozen(Player player){
		BFreeze.frozen.remove(getUUID(player));
	}
	
	//sets a player to "unfrozen"
	public static void setUnfrozen(Player player){
		BFreeze.unfrozen.add(getUUID(player));
	}
	
	//sets a player to "players"
	public static void setPlaying(Player player){
		BFreeze.players.add(getUUID(player));
	}
	
	public static void removePlaying(Player player){
		BFreeze.players.remove(getUUID(player));
	}
	
	//sets a player to "taggers"
	public static void setTagging(Player player){
		BFreeze.taggers.add(getUUID(player));
	}
	
	public static void removeTagging(Player player){
		BFreeze.taggers.remove(getUUID(player));
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
	
	//unfreezes a player
}
