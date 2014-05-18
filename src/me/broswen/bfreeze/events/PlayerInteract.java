package me.broswen.bfreeze.events;

import me.broswen.bfreeze.API;
import me.broswen.bfreeze.BFreeze;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerInteract implements Listener{
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event){
		final Player player = event.getPlayer();
		
		ItemStack powerPot = new ItemStack(Material.POTION);
		ItemMeta ppim = powerPot.getItemMeta();
		ppim.setDisplayName(ChatColor.AQUA + "Power Potion");
		powerPot.setItemMeta(ppim);
		
		if(API.isPlaying(player) && BFreeze.gameStarted){
			if(event.getAction() == Action.PHYSICAL && event.getClickedBlock().getType() == Material.SOIL){
				event.setCancelled(true);
				return;
			}
			
			//uses the power potion
			if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK){
				
				
				if(player.getItemInHand().equals(powerPot)){
					
					if(!API.isTagger(player)){
						return;
					}
					
					event.setCancelled(true);
					
					if(!API.isPPCooldown(player)){
						
						API.sendMessage(player, "You used the Power Potion");
						player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 2));
						
						Bukkit.getScheduler().scheduleSyncDelayedTask(BFreeze.plugin, new Runnable(){
							@Override
							public void run() {
								API.removePPCooldown(player);
								if(API.isTagger(player)){
									API.sendMessage(player, "You can use the Power Potion again!");
								}
							}
							
						}, BFreeze.config.getInt("power-potion-delay") * 20);
						
						API.setPPCooldown(player);
						return;
					}
					API.sendMessage(player, "You can only use the Power Potion every " + BFreeze.config.getInt("power-potion-delay") + " seconds!");
				}
			}
		}
	}

}
