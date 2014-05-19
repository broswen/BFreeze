package me.broswen.bfreeze.events;

import me.broswen.bfreeze.API;
import me.broswen.bfreeze.BFreeze;
import me.broswen.bfreeze.utils.GameManager;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class EntityDamageByEntity implements Listener{
	
	ItemStack pocketSand = new ItemStack(Material.SAND);
	ItemMeta psim = pocketSand.getItemMeta();
	
	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event){
		psim.setDisplayName(ChatColor.GOLD + "Pocket Sand");
		pocketSand.setItemMeta(psim);
		
		if(event.getEntity() instanceof Player){
			Player player = (Player) event.getEntity();
			
			if(!(event.getDamager() instanceof Player)){
				return;
			}
			
			Player damager = (Player) event.getDamager();
			
			if(!API.isPlaying(damager) || !BFreeze.gameStarted){
				return;
			}
			
			if(!API.isPlaying(player) || !BFreeze.gameStarted){
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
				
				if(damager.getLocation().distance(player.getLocation()) > 4){
					return;
				}
				
				if(!BFreeze.taggersStarted){
					return;
				}
				
				if(player.hasPotionEffect(PotionEffectType.INVISIBILITY)){
					return;
				}
								
				API.broadcastToPlayers(ChatColor.BLUE + player.getName() + " has been frozen by " + damager.getName() + "!");
				API.freezePlayer(player);
				
				if(API.hasTaggerWon()){
					API.taggerWin(damager);
				}
				return;
			}
			
			if(API.isTagger(player)){
				if(damager.getItemInHand().equals(pocketSand)){
					damager.getInventory().setItemInHand(null);
					API.sendMessage(damager, "You threw sand in " + player.getName() + "'s eyes!");
					API.sendMessage(player, damager.getName() + " threw sand in your eyes!");
					player.getWorld().playEffect(player.getLocation().add(0, 1, 0), Effect.STEP_SOUND, 12);
					player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, 3));
				}
				return;
			}
			
			if(!API.isFrozen(player)){
				return;
			}
			
			if(API.isUnfrozen(damager)){
				//unfreeze
				API.broadcastToPlayers(ChatColor.GREEN + player.getName() + " has been unfrozen by " + damager.getName() + "!");
				API.unfreeze(player);
			}
		}
	}
}
