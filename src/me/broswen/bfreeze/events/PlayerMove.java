package me.broswen.bfreeze.events;

import me.broswen.bfreeze.API;
import me.broswen.bfreeze.BFreeze;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMove implements Listener{
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event){
		Player player = event.getPlayer();
		
		if(!API.isPlaying(player)){
			return;
		}
		
		if(!BFreeze.gameStarted){
			return;
		}
		
		if(API.isFrozen(player)){
			if(event.getFrom().getY() < event.getTo().getY() || event.getFrom().getX() != event.getTo().getX() || event.getFrom().getZ() != event.getTo().getZ()){
				player.teleport(event.getFrom());
			}
		}
			
		if(API.isTagger(player) && !BFreeze.taggersStarted){
			if(event.getFrom().getY() < event.getTo().getY() || event.getFrom().getX() != event.getTo().getX() || event.getFrom().getZ() != event.getTo().getZ()){
				player.teleport(event.getFrom());
			}
		}
	}
}
