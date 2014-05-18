package me.broswen.bfreeze.events;

import me.broswen.bfreeze.API;
import me.broswen.bfreeze.BFreeze;
import me.broswen.bfreeze.utils.GameManager;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener{

	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event){
		Player player = event.getPlayer();
		
		if(API.isPlaying(player)){
			API.teleportPlayer(player, BFreeze.lobbySpawn);
			
			API.removePlaying(player);
			API.removeFrozen(player);
			API.removeTagging(player);
			API.removeUnfrozen(player);
			
			API.broadcastToPlayers(player.getName() + " has left the game!");
			BFreeze.totalPlaying--;
			
			if(API.isTagger(player)){
				BFreeze.totalTagging--;
			}
			
			if(API.isFrozen(player)){
				API.unfreeze(player);
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
		}
	}
}
