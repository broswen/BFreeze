package me.broswen.bfreeze.utils;

import java.util.Random;

import me.broswen.bfreeze.API;
import me.broswen.bfreeze.BFreeze;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SpawnHandler {
	
	public static void teleportToArena(){
		
		API.broadcastToPlayers("There Are " + BFreeze.config.getInt("tagger-delay") + " Seconds Until The Tagger Is Released!");
		
		//start tagger delay
		Bukkit.getScheduler().scheduleSyncDelayedTask(BFreeze.plugin, new Runnable(){
			@Override
			public void run() {
				if(BFreeze.gameStarted){
					API.broadcastToPlayers("The Tagger Has Been Released!");
					BFreeze.taggersStarted = true;
				}
			}
		}, BFreeze.config.getInt("tagger-delay") * 20);
		
		API.chooseRandomTagger();
		
		for (Player p : Bukkit.getOnlinePlayers()) {
			if(API.isPlaying(p)){
				
				API.resetPotions(p);
				
				API.resetPlayer(p);
				BFreeze.totalUnfrozen++;
				API.teleportPlayer(p, BFreeze.playerSpawn);
				API.setUnfrozen(p);
				
				if(API.isTagger(p)){
					API.teleportPlayer(p, BFreeze.taggerSpawn);
					API.givePowerPotion(p);
					API.giveJumpBoots(p);
					API.giveTaggerArmor(p);
				}
				
				if(!API.isTagger(p)){
					API.giveInvisBottle(p);
					API.givePocketSand(p);
					p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 72000, 0));
				}
			}
		}
	}
	
	public static void teleportToLobby(){
		for (Player p : Bukkit.getOnlinePlayers()) {
			if(API.isPlaying(p)){
				API.teleportPlayer(p, BFreeze.lobbySpawn);
				API.resetPlayer(p);
				
				API.resetPotions(p);
				
				if(API.isFrozen(p)){
					API.unfreeze(p);
				}
			}
		}
	}
}
