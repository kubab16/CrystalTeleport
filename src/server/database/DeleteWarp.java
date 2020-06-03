package server.database;

import java.sql.*;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import crystal.teleport.CrystalTeleport;

public class DeleteWarp{
	private Connection connection = CrystalTeleport.plugin.getConetion();
	private boolean redy;
	public DeleteWarp(String name, Player player)
	{
		redy = Delete(name);
		if (redy)
			player.sendMessage(ChatColor.GREEN+"Warp usunięty!");
		else
			player.sendMessage(ChatColor.RED+"Warp nie usunięty!");
	}
	
	public boolean Delete(String name)
	{
		try {
			PreparedStatement statement = connection.prepareStatement("DELETE FROM crystalwarp WHERE warp=?");
			statement.setString(1, name);
			if (statement.executeUpdate() >0)
			{
				return true;
			}
			else
			{
				return false;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return true;
		}
	}
	
	public boolean redy()
	{
		return redy;
	}
}
