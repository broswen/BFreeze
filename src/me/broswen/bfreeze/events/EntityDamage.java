package me.broswen.bfreeze.events;

import me.broswen.bfreeze.API;
import me.broswen.bfreeze.BFreeze;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityDamage implements Listener{
	
	@EventHandler
	public void onEntityDamage(EntityDamageEvent event){
		
		if(event.getEntity() instanceof Player){
			Player player = (Player) event.getEntity();
			
			if(API.isPlaying(player) && BFreeze.gameStarted){
				event.setCancelled(true);
			}
		}
	}
}
