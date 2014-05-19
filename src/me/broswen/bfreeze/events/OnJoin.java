package me.broswen.bfreeze.events;

import me.broswen.bfreeze.BFreeze;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class OnJoin implements Listener{
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event){
		if(BFreeze.config.getBoolean("join-on-join")){
			event.getPlayer().chat("/freeze join");
		}
	}

}
