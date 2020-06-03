package crystal.teleport;
import java.sql.Connection;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import server.database.ConetionConf;
import server.database.CreateConetion;

public class CrystalTeleport extends JavaPlugin {
	
	public static CrystalTeleport plugin;
	public CreateConetion connection; 
	
	@Override
    public void onEnable(){
		Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Crystal  teleport begin work...");
		
		getCommand("crconf").setExecutor(new ConetionConf() );
		plugin = this;
		connection = new CreateConetion();
		this.getServer().getPluginManager().registerEvents(new CrystaEvents(), this);
		CommandLoad();
		Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Crystal  teleport is working.");
	}
 
    @Override
    public void onDisable() {
    	Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Crystal teleport end work...");
    }
    
    public Connection getConetion()
    {
    	return connection.getConnection();
    }
    
    private void CommandLoad()
    {
    	getCommand("crystal").setExecutor(new GetCrystal() );
		getCommand("crystalhome").setExecutor(new CrystalHome() );
		getCommand("crystalwarp").setExecutor(new CrystalWarp() );
    }
    
}
