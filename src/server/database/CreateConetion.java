package server.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;

import crystal.teleport.CrystalTeleport;

public class CreateConetion implements Listener{
	 public Connection connection;
	 private String host, database, username, password, port;
	 public String dataConect;
	public CreateConetion()
	{
		loadConfig();
		mysqlSetup();
		createTable("CREATE TABLE IF NOT EXISTS `CrystalHome` "
					+ "(id INT auto_increment primary key"
					+ ",uuid VARCHAR(50)"
					+ ", x INT"
					+ ", y INT"
					+ ", z INT"
					+ ", word VARCHAR(50),"
					+ "  UNIQUE KEY `uuid_UNIQUE` (`uuid`)"
					+ ")","CrystalHome");
		createTable("CREATE TABLE IF NOT EXISTS `CrystalWarp` "
					+ "(id INT auto_increment primary key"
					+ ",warp VARCHAR(20)"
					+ ", x INT"
					+ ", y INT"
					+ ", z INT"
					+ ", word VARCHAR(50)"
					+ ",UNIQUE KEY `warp_UNIQUE` (`warp`))"
					,"CrystalWarp");
		
	}
	
	public void loadConfig(){
		CrystalTeleport.plugin.getConfig().options().copyDefaults(true);
		CrystalTeleport.plugin.saveConfig();
	}
	
	public void mysqlSetup() {
		host = CrystalTeleport.plugin.getConfig().getString("host");
		port = CrystalTeleport.plugin.getConfig().getString("port");
		database = CrystalTeleport.plugin.getConfig().getString("database");
		username = CrystalTeleport.plugin.getConfig().getString("username");
		password = CrystalTeleport.plugin.getConfig().getString("password");

		try {

			synchronized (this) {
				if (getConnection() != null && !getConnection().isClosed()) {
					return;
				}

				Class.forName("com.mysql.jdbc.Driver");
				setConnection(
						DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database,
								this.username, this.password));

				Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Crystal  MYSQL CONNECTED");
			}
		} catch (SQLException e) {
			Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Data bases error!!");
		} catch (ClassNotFoundException e) {
			Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Data bases error!!");
		}
		
	}
	
	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}//Disable option
	
	private void createTable(String definition, String name)
	{
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate(definition);
			Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Crystal  " + name + " Table OK");
		} catch (SQLException e) {
			Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Crystal  Warn " + name + " table!!");
			e.printStackTrace();
		}
		
	}
}
