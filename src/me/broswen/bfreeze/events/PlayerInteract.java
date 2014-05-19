package me.broswen.bfreeze.events;

import me.broswen.bfreeze.API;
import me.broswen.bfreeze.BFreeze;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
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
		
		//POWERPOTION
		ItemStack powerPot = new ItemStack(Material.POTION);
		ItemMeta ppim = powerPot.getItemMeta();
		ppim.setDisplayName(ChatColor.AQUA + "Power Potion");
		powerPot.setItemMeta(ppim);
		
		//INVISBOTTLE
		ItemStack invisBottle = new ItemStack(Material.GLASS_BOTTLE);
		ItemMeta ibim = invisBottle.getItemMeta();
		ibim.setDisplayName(ChatColor.AQUA + "Invisibility Liquid");
		invisBottle.setItemMeta(ibim);
		
		//JUMPBOOTS
		ItemStack jumpBoots = new ItemStack(Material.IRON_BOOTS);
		ItemMeta jbim = jumpBoots.getItemMeta();
		jbim.setDisplayName(ChatColor.RED + "Air Jordans");
		jumpBoots.setItemMeta(jbim);
		
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
				
				if(player.getItemInHand().equals(jumpBoots)){
					if(!API.isTagger(player)){
						return;
					}
					
					event.setCancelled(true);
					
					if(!API.isJBCooldown(player)){
						
						API.sendMessage(player, "You used your Air Jordans!");
						player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 100, 4));
						
						Bukkit.getScheduler().scheduleSyncDelayedTask(BFreeze.plugin, new Runnable(){
							@Override
							public void run() {
								API.removeJBCooldown(player);
								if(API.isTagger(player)){
									API.sendMessage(player, "You can use your Air Jordans again!");
								}
							}
							
						}, BFreeze.config.getInt("jump-boots-delay") * 20);
						
						API.setJBCooldown(player);
						return;
					}
					API.sendMessage(player, "You can only use your Air Jordans every " + BFreeze.config.getInt("jump-boots-delay") + " seconds!");
				}
				
				if(player.getItemInHand().equals(invisBottle)){
					
					if(API.isTagger(player)){
						return;
					}
					
					event.setCancelled(true);
					
					player.getInventory().setItemInHand(null);
					API.sendMessage(player, "You poured invisibility liquid on yourself! Gross!!!");
					player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 100, 1));
					player.getWorld().playSound(player.getLocation(), Sound.ZOMBIE_UNFECT, 1, 2);
				}
			}
		}
	}

}
