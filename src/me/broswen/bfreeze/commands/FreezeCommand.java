package me.broswen.bfreeze.commands;

import me.broswen.bfreeze.API;
import me.broswen.bfreeze.BFreeze;
import me.broswen.bfreeze.utils.GameManager;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

public class FreezeCommand implements CommandExecutor{

	public FreezeCommand(BFreeze bFreeze) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("freeze")){
			if(!(sender instanceof Player)){
				sender.sendMessage("You must be a player to do that!");
				return true;
			}
			Player player = (Player) sender;
			
			if(args.length != 1){
				API.sendMessage(player, "Usage: /freeze 'join/leave'");
				return true;
			}
			
			if(args[0].equalsIgnoreCase("join")){
				//join game
				
				if(!sender.hasPermission("bfreeze.join")){
					API.sendMessage(player, ChatColor.RED + "You don't have permission!");
					return true;
				}
				
				if(BFreeze.gameStarted){
					API.sendMessage(player, "The game has already started!");
					return true;
				}
				
				if(API.isPlaying(player)){
					API.sendMessage(player, "You are already in a game!");
					return true;
				}
				
				if(BFreeze.totalPlaying == BFreeze.config.getInt("max-players")){
					API.sendMessage(player, "There are already the max amount of players in that game!");
					return true;
				}
				
				API.setPlaying(player);
				BFreeze.totalPlaying++;
				API.broadcastToPlayers(player.getName() + " Joined The Game! [" + BFreeze.totalPlaying + "/6]");
				API.teleportPlayer(player, BFreeze.lobbySpawn);
				
				if(BFreeze.totalPlaying == BFreeze.config.getInt("max-players")){
					GameManager.startGame();
				}
				
				return true;
			}
			
			if(args[0].equalsIgnoreCase("leave")){
				//leave game
				
				if(!sender.hasPermission("bfreeze.leave")){
					API.sendMessage(player, ChatColor.RED + "You don't have permission!");
					return true;
				}
				
				if(API.isPlaying(player) == false){
					API.sendMessage(player, "You are not in a game!");
					return true;
				}
				API.broadcastToPlayers(player.getName() + " Left The Game!");
				API.teleportPlayer(player, BFreeze.lobbySpawn);
				BFreeze.totalPlaying--;
				
				if(API.isTagger(player)){
					BFreeze.totalTagging--;
				}
				
				if(API.isFrozen(player)){
					BFreeze.totalFrozen--;
				}
				
				if(API.isUnfrozen(player)){
					BFreeze.totalUnfrozen--;
				}
				
				if(BFreeze.totalTagging <= 0 && BFreeze.gameStarted){
					GameManager.endGame();
				}
				
				if(BFreeze.totalPlaying < BFreeze.config.getInt("min-players") && BFreeze.gameStarted){
					GameManager.endGame();
				}
				
				API.removePlaying(player);
				API.removeFrozen(player);
				API.removeTagging(player);
				API.removeUnfrozen(player);
				
				return true;
			}
			
			if(args[0].equalsIgnoreCase("start")){
				//start game
				
				if(!sender.hasPermission("bfreeze.start")){
					API.sendMessage(player, ChatColor.RED + "You don't have permission!");
					return true;
				}
				
				if(!API.isPlaying(player)){
					API.sendMessage(player, "You must be in a game to start it!");
					return true;
				}
				
				if(BFreeze.gameStarted){
					API.sendMessage(player, "The game has already started!");
					return true;
				}
				
				if(BFreeze.totalPlaying < BFreeze.config.getInt("min-players")){
					API.sendMessage(player, "There needs to be a minimum of " + BFreeze.config.getInt("min-players") + " players!");
					return true;
				}
				
				GameManager.startGame();
				
				return true;
			}
			
			if(args[0].equalsIgnoreCase("stop")){
				//stop game
				if(!sender.hasPermission("bfreeze.stop")){
					API.sendMessage(player, ChatColor.RED + "You don't have permission!");
					return true;
				}
				
				if(!API.isPlaying(player)){
					API.sendMessage(player, "You must be in a game to stop it!");
					return true;
				}
				
				if(!BFreeze.gameStarted){
					API.sendMessage(player, "The game has already stopped!");
					return true;
				}
				
				GameManager.endGame();
				
				return true;
			}
			
			API.sendMessage(player, "Usage: /freeze 'join/leave/start/stop'");
		}
		
		return true;
	}
}
