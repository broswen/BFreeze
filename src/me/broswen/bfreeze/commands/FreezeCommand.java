package me.broswen.bfreeze.commands;

import me.broswen.bfreeze.API;
import me.broswen.bfreeze.BFreeze;
import me.broswen.bfreeze.utils.GameManager;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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
				
				if(BFreeze.gameStarted){
					API.sendMessage(player, "The game has already started!");
					return true;
				}
				
				if(API.isPlaying(player)){
					API.sendMessage(player, "You are already in a game!");
					return true;
				}
				
				API.setPlaying(player);
				API.sendMessage(player, "You joined the game!");
				API.broadcastToPlayers(player.getName() + " Joined The Game!");
				API.teleportPlayer(player, BFreeze.lobbySpawn);
				
				return true;
			}
			
			if(args[0].equalsIgnoreCase("leave")){
				//leave game
				
				if(API.isPlaying(player) == false){
					API.sendMessage(player, "You are not in a game!");
					return true;
				}
				
				API.removePlaying(player);
				API.sendMessage(player, "You left the game!");
				API.broadcastToPlayers(player.getName() + " Left The Game!");
				API.teleportPlayer(player, BFreeze.lobbySpawn);
				
				return true;
			}
			
			if(args[0].equalsIgnoreCase("start")){
				//start game
				
				if(!API.isPlaying(player)){
					API.sendMessage(player, "You must be in a game to start it!");
					return true;
				}
				
				if(BFreeze.gameStarted){
					API.sendMessage(player, "The game has already started!");
					return true;
				}
				
				GameManager.startGame();
				
				return true;
			}
			
			if(args[0].equalsIgnoreCase("stop")){
				//stop game
				
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
