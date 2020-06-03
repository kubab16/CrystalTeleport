package server.database;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import crystal.teleport.CrystalTeleport;

public class ConetionConf implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String alias, String[] arg) {
		if(!sender.isOp()) {
			return false;
		}
		Player player = (Player) sender;
		player.sendMessage("Start sqlConfig...");
		if ( arg.length == 5)
		{
			player.sendMessage("Start uploadin data...");
			CrystalTeleport.plugin.getConfig().set("username", arg[0]);
			CrystalTeleport.plugin.getConfig().set("password", arg[1]);
			CrystalTeleport.plugin.getConfig().set("host", arg[2]);
			CrystalTeleport.plugin.getConfig().set("database", arg[3]);
			CrystalTeleport.plugin.getConfig().set("port", arg[4]);
			CrystalTeleport.plugin.saveConfig();
			player.sendMessage("Configuration complete.");
			CrystalTeleport.plugin.getLogger().info("CrystalTeleport change Mysql config");
			return true;
		}
		else
		{
			player.sendMessage("Wrong data plis corect it!");
			return false;
		}
	}

}


