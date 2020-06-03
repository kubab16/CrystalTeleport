package crystal.teleport;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import server.database.GetHome;
import server.database.GetWarp;

public class CrystaEvents implements Listener{
	public CrystaEvents(){
		Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Crystal Event loadet...");
	}
	
	@EventHandler
	public void onPlayerClicks(PlayerInteractEvent event)
	{
		Player player = event.getPlayer();
		Action action = event.getAction();
		if ( action.equals(Action.RIGHT_CLICK_BLOCK) || action.equals(Action.RIGHT_CLICK_AIR))
		{
			
			if (player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.GOLD+"Kryształ teleportujący") ) {
				String location = player.getInventory().getItemInMainHand().getItemMeta().getLocalizedName().toLowerCase();
				
				if(player.getInventory().getItemInMainHand().getAmount() > 1)
				{
					ItemStack item1,item2;
					item1 = player.getInventory().getItemInMainHand().clone();
					item2 = item1.clone();
					
					int i = item1.getAmount() - 1;
					item1.setAmount(i);
					item2.setAmount(1);
					ItemMeta meta2 = item2.getItemMeta();
					int duburarity = meta2.getCustomModelData()-1;
					List<String> lore2 = new ArrayList<>();
					lore2 = meta2.getLore();
					
					String loredub =  "Zostało: "+ duburarity;
					
					lore2.set(1, loredub);
					meta2.setLore(lore2);
					meta2.setCustomModelData(duburarity);
					
					player.getInventory().remove(player.getInventory().getItemInMainHand());
					
					item2.setItemMeta(meta2);
					player.getInventory().addItem(item1);
					if (duburarity > 0)
						player.getInventory().addItem(item2);
				}
				else
				{
					ItemStack item2;
					item2 = player.getInventory().getItemInMainHand().clone();
					ItemMeta meta1 = item2.getItemMeta();
					player.getInventory().removeItem(player.getInventory().getItemInMainHand());
					int duburarity = meta1.getCustomModelData()-1;
					List<String> lore1 = new ArrayList<>();
					lore1 = meta1.getLore();
					String loredub =  "Zostało: "+ duburarity;
					lore1.set(1, loredub);
					meta1.setLore(lore1);
					meta1.setCustomModelData(duburarity);
					item2.setItemMeta(meta1);
					if (duburarity > 0)
						player.getInventory().addItem(item2);					
				}
				
				if (location.equals("home")) //Go home
				{
					String IDplayer = player.getUniqueId().toString();
					GetHome home = new GetHome(IDplayer);
					if(home.getExist() == false)
					{
						player.sendMessage( ChatColor.YELLOW+"Nie masz przypisanego domu! Użyj komędy [/crystalhome] aby go przypisać!");
						return;
					}
					else 
					{
						Location PlayerLocation = player.getLocation();
						double x ,y,z;
						x = home.getX();
						y = home.getY();
						z = home.getZ();
						World wordl = Bukkit.getServer().getWorld(UUID.fromString(home.getWord()));
						PlayerLocation = new Location(wordl,x,y,z,0,0);
						player.teleport(PlayerLocation);
					}
					 
				}
				else //Warp
				{
					GetWarp warp = new GetWarp(location);
					if(warp.getExist() == false)
					{
						player.sendMessage( ChatColor.YELLOW+"Brak podanego warpu!");
						return;
					}
					else 
					{
						player.sendMessage( ChatColor.GREEN+"Tleportacia do " + location);
						Location PlayerLocation = player.getLocation();
						double x ,y,z;
						x = warp.getX();
						y = warp.getY();
						z = warp.getZ();
						World wordl = Bukkit.getServer().getWorld(UUID.fromString(warp.getWord()));
						PlayerLocation = new Location(wordl,x,y,z,0,0);
						player.teleport(PlayerLocation);
					}
				}
	         } 
		}
	}	
}