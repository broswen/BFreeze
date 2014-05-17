package me.broswen.bfreeze.events;

import me.broswen.bfreeze.API;
import me.broswen.bfreeze.BFreeze;
import me.broswen.bfreeze.utils.GameManager;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamageByEntity implements Listener{
	
	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event){
		
		if(event.getEntity() instanceof Player){
			Player player = (Player) event.getEntity();
			
			if(!(event.getDamager() instanceof Player)){
				return;
			}
			
			Player damager = (Player) event.getDamager();
			
			if(!API.isPlaying(damager) && !BFreeze.gameStarted){
				return;
			}
			
			if(!API.isPlaying(player) && !BFreeze.gameStarted){
				return;
			}
			
			event.setCancelled(true);
			
			if(API.isTagger(damager)){
				//freeze
				
				if(API.isTagger(player)){
					API.sendMessage(damager, "You can't freeze other taggers!");
					return;
				}
				
				if(!API.isUnfrozen(player)){
					return;
				}
								
				API.sendMessage(damager, "You froze " + player.getName() + "!");
				API.sendMessage(player, "You have been frozen by " + damager.getName() + "!");
				API.freezePlayer(player);
				
				if(BFreeze.totalUnfrozen == 0){
					GameManager.endGame();
				}
			}
			
			if(API.isUnfrozen(damager)){
				//unfreeze
				
				if(API.isUnfrozen(player)){
					return;
				}
				
				if(API.isTagger(player)){
					return;
				}
				
				if(API.isTagger(damager)){
					return;
				}
				
				if(!API.isFrozen(player)){
					return;
				}
				
				API.sendMessage(player, "You have been unfrozen by " + damager.getName() + "!");
				API.sendMessage(damager, "You have unfrozen " + player.getName() + "!");
				API.unfreeze(player);
			}
		}
	}
}
