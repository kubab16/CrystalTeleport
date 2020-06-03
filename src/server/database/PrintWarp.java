package server.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import crystal.teleport.CrystalTeleport;

public class PrintWarp {
	private Connection connection = CrystalTeleport.plugin.getConetion();
	private boolean redy;
	
	public PrintWarp(Player player)
	{
		redy = getWarp(player);
	}
	
	public boolean getWarp(Player player)
	{
		try {
			java.sql.PreparedStatement statement = connection
				.prepareStatement("SELECT warp FROM crystalwarp");
			 ResultSet result = statement.executeQuery();
			 if( result.next())
			 {
				 player.sendMessage(ChatColor.GREEN+"Dostępne Warpy: ");
				 do
				 {
					 player.sendMessage(ChatColor.AQUA+"-"+result.getString("warp"));
				 }
				 while(result.next() );
				 return true;
			 }
			 else
			 {
				 player.sendMessage(ChatColor.RED+"Brak dostępnych warpów!");
				 return false;
			 }
		} catch (SQLException e) {			
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean redy()
	{
		return redy;
	}
}
