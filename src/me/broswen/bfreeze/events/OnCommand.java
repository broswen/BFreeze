package me.broswen.bfreeze.events;

import me.broswen.bfreeze.API;
import me.broswen.bfreeze.BFreeze;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class OnCommand implements Listener{

	@EventHandler
	
	public void onCommandPreprocess(PlayerCommandPreprocessEvent event){
		Player player = event.getPlayer();
		
		if(API.isPlaying(player) && BFreeze.gameStarted){
			
			if(player.hasPermission("bfreeze.admin")){
				return;
			}
			
			if(event.getMessage().startsWith("/freeze")){
				return;
			}
			event.setCancelled(true);
			API.sendMessage(player, "You cannot use non /freeze commands in game!");
		}
	}
}
