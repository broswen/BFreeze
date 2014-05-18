package me.broswen.bfreeze.utils;

import org.bukkit.Bukkit;
import org.bukkit.Sound;

import me.broswen.bfreeze.API;
import me.broswen.bfreeze.BFreeze;

public class GameManager {

	public static void startGame(){
		
		BFreeze.gameStarted = true;
		API.broadcastToPlayers("The Game Has Started!");
		SpawnHandler.teleportToArena();
		BFreeze.playerSpawn.getWorld().playSound(BFreeze.playerSpawn, Sound.LEVEL_UP, 2, 1);
	}
	
	public static void endGame(){
		
		API.broadcastToPlayers("The Game Has Ended!");
		BFreeze.playerSpawn.getWorld().playSound(BFreeze.playerSpawn, Sound.LEVEL_UP, 2, 1);
		
		Bukkit.getScheduler().scheduleSyncDelayedTask(BFreeze.plugin, new Runnable(){

			@Override
			public void run() {
				SpawnHandler.teleportToLobby();
				
				BFreeze.gameEnded = true;
				BFreeze.gameStarted = false;
				BFreeze.taggersStarted = false;
				BFreeze.totalFrozen = 0;
				BFreeze.totalPlaying = 0;
				BFreeze.totalUnfrozen = 0;
				BFreeze.totalTagging = 0;
				
				BFreeze.players.clear();
				BFreeze.taggers.clear();
				BFreeze.frozen.clear();
				BFreeze.unfrozen.clear();
			}
			
		}, 60L);
	}
}
