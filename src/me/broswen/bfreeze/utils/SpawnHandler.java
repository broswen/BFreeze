package me.broswen.bfreeze.utils;

import me.broswen.bfreeze.API;
import me.broswen.bfreeze.BFreeze;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class SpawnHandler {
	
	public static void teleportToArena(){
		for (Player p : Bukkit.getOnlinePlayers()) {
			if(API.isPlaying(p)){
				API.teleportPlayer(p, BFreeze.playerSpawn);
			}
		}
	}
	
	public static void teleportToLobby(){
		for (Player p : Bukkit.getOnlinePlayers()) {
			if(API.isPlaying(p)){
				API.teleportPlayer(p, BFreeze.lobbySpawn);
			}
		}
	}
}
