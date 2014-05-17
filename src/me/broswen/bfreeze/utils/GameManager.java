package me.broswen.bfreeze.utils;

import me.broswen.bfreeze.API;
import me.broswen.bfreeze.BFreeze;

public class GameManager {

	public static void startGame(){
		
		BFreeze.gameStarted = true;
		SpawnHandler.teleportToArena();
		
		//send players to spawn if they are in lobby
		//add to teams/ totalunfrozen
		
		API.broadcastToPlayers("The Game Has Started!");
	}
	
	public static void endGame(){
		SpawnHandler.teleportToLobby();
		API.broadcastToPlayers("The Game Has Ended!");
		
		BFreeze.gameEnded = true;
		BFreeze.gameStarted = false;
		BFreeze.totalFrozen = 0;
		BFreeze.totalPlaying = 0;
		BFreeze.totalUnfrozen = 0;
		
		BFreeze.players.clear();
		BFreeze.taggers.clear();
		BFreeze.frozen.clear();
		BFreeze.unfrozen.clear();

		//reset?
	}
}
