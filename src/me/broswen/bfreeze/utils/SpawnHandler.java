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
		
		API.broadcastToPlayers("You have " + BFreeze.config.getInt("tagger-delay") + " Seconds To Run!");
		
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
		
		Random rand = new Random();
		int randomNumber = rand.nextInt(BFreeze.totalPlaying);
		int int1 = 0;
		
		
		for (Player p : Bukkit.getOnlinePlayers()) {
			if(API.isPlaying(p)){
				
				for (PotionEffect effect : p.getActivePotionEffects()){
					p.removePotionEffect(effect.getType());
				}
				
				p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 72000, 0));
				
				p.setGameMode(GameMode.SURVIVAL);
				p.setFoodLevel(20);
				p.getInventory().clear();
				p.getInventory().setArmorContents(null);
				BFreeze.totalUnfrozen++;
				API.teleportPlayer(p, BFreeze.playerSpawn);
				API.setUnfrozen(p);
				
				if(int1 == randomNumber){
					API.setTagging(p);
					API.removeUnfrozen(p);
					for (PotionEffect effect : p.getActivePotionEffects()){
						p.removePotionEffect(effect.getType());
					}
					BFreeze.totalUnfrozen--;
					BFreeze.totalTagging++;
					API.sendMessage(p, "You are now a tagger!");
					API.broadcastToPlayers(p.getName() + " Is Now The Tagger! RUN!!!");
					API.teleportPlayer(p, BFreeze.taggerSpawn);
					API.givePowerPotion(p);
					API.giveTaggerArmor(p);
				}
				int1++;
				
				if(!API.isTagger(p)){
					API.givePocketSand(p);
				}
			}
		}
	}
	
	public static void teleportToLobby(){
		for (Player p : Bukkit.getOnlinePlayers()) {
			if(API.isPlaying(p)){
				API.teleportPlayer(p, BFreeze.lobbySpawn);
				p.setFoodLevel(20);
				p.getInventory().clear();
				p.getInventory().setArmorContents(null);
				
				for (PotionEffect effect : p.getActivePotionEffects()){
					p.removePotionEffect(effect.getType());
				}
				
				if(API.isFrozen(p)){
					API.unfreeze(p);
				}
			}
		}
	}
}
