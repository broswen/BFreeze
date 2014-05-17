package me.broswen.bfreeze.utils;

import me.broswen.bfreeze.API;
import me.broswen.bfreeze.BFreeze;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

public class SpawnHandler {
	
	public static void teleportToArena(){
		for (Player p : Bukkit.getOnlinePlayers()) {
			if(API.isPlaying(p)){
				
				for (PotionEffect effect : p.getActivePotionEffects()){
					p.removePotionEffect(effect.getType());
				}
				BFreeze.totalUnfrozen++;
				API.teleportPlayer(p, BFreeze.playerSpawn);
				API.setUnfrozen(p);
			}
		}
	}
	
	public static void teleportToLobby(){
		for (Player p : Bukkit.getOnlinePlayers()) {
			if(API.isPlaying(p)){
				API.teleportPlayer(p, BFreeze.lobbySpawn);
				
				if(API.isFrozen(p)){
					API.unfreeze(p);
				}
			}
		}
	}
}
