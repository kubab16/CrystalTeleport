package server.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import crystal.teleport.CrystalTeleport;

public class GetWarp {
	private Connection connection = CrystalTeleport.plugin.getConetion();
	private int x,y,z;
	private String Word;
	private Boolean exist;
	public GetWarp(String WarpName)
	{
		try {
			java.sql.PreparedStatement statement = connection
					.prepareStatement("SELECT * FROM CrystalWarp WHERE warp=?");
			statement.setString(1, WarpName);
			ResultSet results = statement.executeQuery();
			if (results.next()) {
			 	this.x = results.getInt("x");
			 	this.y = results.getInt("y");
			 	this.z = results.getInt("z");
			 	this.Word = results.getString("word");
			 	exist = true;
			}
			else
			{
				exist = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public Boolean getExist()
	{
		return this.exist;
	}
	
	public int getX()
	{
		return this.x;
	}
	
	public int getY()
	{
		return this.y;
	}
	
	public int getZ()
	{
		return this.z;
	}
	
	public String getWord()
	{
		return this.Word;
	}
}
