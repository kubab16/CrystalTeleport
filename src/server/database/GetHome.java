package server.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import crystal.teleport.CrystalTeleport;

public class GetHome {
	private Connection connection = CrystalTeleport.plugin.getConetion();
	private double x,y,z;
	private String Word;
	private Boolean exist = true;
	
	public GetHome(String uuid)
	{
		exist = selectHome(uuid);
	}
	
	public boolean selectHome(String uuid)
	{
		try {
			PreparedStatement statement = connection
					.prepareStatement("SELECT x,y,z,word FROM crystalhome WHERE uuid=?");
			statement.setString(1, uuid);
			ResultSet results = statement.executeQuery();
			if (results.next()) {
				x = results.getDouble("x");
			 	y = results.getDouble("y");
			 	z = results.getDouble("z");
			 	Word = results.getString("word");
			 	return true;
			}
			else
			{
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public Boolean getExist()
	{
		return this.exist;
	}
	
	public double getX()
	{
		return this.x;
	}
	
	public double getY()
	{
		return this.y;
	}
	
	public double getZ()
	{
		return this.z;
	}
	
	public String getWord()
	{
		return this.Word;
	}
}
