package crystal.teleport;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CrystalCreate {

	ItemStack itemstack = null;
	public CrystalCreate(){
		itemstack = new ItemStack(Material.DIAMOND);
	}
	public ItemStack crystal(String location, String name, int dubularity){
		
		
		ItemMeta meta = itemstack.getItemMeta();
		meta.setDisplayName(ChatColor.GOLD+"Kryształ teleportujący");
		 
		
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.GREEN+"Kryształ teleportujący do "+name);
		lore.add("Zostało: "+ dubularity);
		lore.add("");
		lore.add("CrystalTeleport");
		meta.setLocalizedName(location);
		meta.setCustomModelData(10);
		meta.setLore(lore);
		meta.hasEnchants();
		
		itemstack.setItemMeta(meta);
		
		return itemstack;	
	}
}
