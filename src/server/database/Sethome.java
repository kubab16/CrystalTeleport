package server.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;


import crystal.teleport.CrystalTeleport;

public class Sethome {
	private Connection connection = CrystalTeleport.plugin.getConetion();
	private Location location;
	private boolean exist;
	public int x,y,z;
	public String uuid;
	public World word;
	public Sethome(Player player)
	{
		 location = player.getLocation();
		 x = location.getBlockX();
		 y = location.getBlockY();
		 z = location.getBlockZ();
		 word = location.getWorld();
		 uuid = player.getUniqueId().toString();
		 try {
			Statement statement = connection.createStatement();
			String query = " REPLACE  INTO crystalhome values (NULL,'"+ uuid +"'," + x +"," + y +"," + z + ",'" + word.getUID().toString() + "')";
			int rowsUpdated = statement.executeUpdate(query);
			if (rowsUpdated > 0) {
			    player.sendMessage(ChatColor.GREEN+"Crystal sethome x:" + x + " y: "+ y + " z: " + z + " świat: "  + word.getName());
			    exist = true;
			}
			else
			{
				player.sendMessage(ChatColor.GREEN+"Crystal sethome nie ustawiony spróbuj ponownie!");
				exist = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public boolean redy()
	{
		return exist;
	}
}
