package me.broswen.bfreeze.events;

import me.broswen.bfreeze.API;
import me.broswen.bfreeze.BFreeze;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlace implements Listener{

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event){
		Player player = event.getPlayer();
		
		if(API.isPlaying(player) && BFreeze.gameStarted){
			event.setCancelled(true);
		}
		
	}
}
