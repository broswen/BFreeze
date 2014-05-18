package me.broswen.bfreeze.commands;

import me.broswen.bfreeze.API;
import me.broswen.bfreeze.BFreeze;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FreezeInfoCommand implements CommandExecutor{
	
	public FreezeInfoCommand(BFreeze bFreeze) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("freezeinfo")){
			
			if(!sender.hasPermission("bfreeze.freezeinfo")){
				sender.sendMessage(ChatColor.RED + "You don't have permission");
				return true;
			}
			
			sender.sendMessage("gameStarted: " + BFreeze.gameStarted);
			sender.sendMessage("gameEnded: " + BFreeze.gameEnded);
			sender.sendMessage("taggersStarted: " + BFreeze.taggersStarted);
			sender.sendMessage("totalUnfrozen: " + BFreeze.totalUnfrozen);
			sender.sendMessage("totalFrozen: " + BFreeze.totalFrozen);
			sender.sendMessage("totalPlaying: " + BFreeze.totalPlaying);
			sender.sendMessage("totalTagging: " + BFreeze.totalTagging);
			
			if(sender instanceof Player){
				Player player = (Player) sender;
				
				player.sendMessage("isFrozen: " + API.isFrozen(player));
				player.sendMessage("isPlaying: " + API.isPlaying(player));
				player.sendMessage("isUnfrozen: " + API.isUnfrozen(player));
				player.sendMessage("isTagger: " + API.isTagger(player));
				
				if(BFreeze.gameStarted){
					player.sendMessage("points: " + API.getPoints(player));
				}
			}
		}
		
		return true;
	}
}
