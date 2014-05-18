package me.broswen.bfreeze.events;

import me.broswen.bfreeze.API;
import me.broswen.bfreeze.BFreeze;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class HungerChange implements Listener{
	
	@EventHandler
	public void onHungerChange(FoodLevelChangeEvent event){
		
		if(!(event.getEntity() instanceof Player)){
			return;
		}
		Player player = (Player) event.getEntity();
		
		if(API.isPlaying(player) && BFreeze.gameStarted){
			event.setCancelled(true);
			player.setFoodLevel(20);
		}
		
		
	}

}
