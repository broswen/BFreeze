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
		BFreeze.gameEnded = true;
		BFreeze.gameStarted = false;
		API.resetArrayLists();

		//reset?
		
		API.broadcastToPlayers("The Game Has Ended!");
	}
}
