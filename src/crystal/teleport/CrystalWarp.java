package crystal.teleport;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import server.database.CreateWarp;
import server.database.DeleteWarp;
import server.database.PrintWarp;

public class CrystalWarp implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String alias, String[] arg) {
		Player player = (Player)sender;
		if (arg.length > 0)
		{
			if(player.isOp())
			{
			switch (arg[0]) {
			case "new":
				if (arg.length == 2) {
					CreateWarp warp = new CreateWarp(player,arg[1]);
					return warp.redy();
				}
				else
				{
					player.sendMessage(ChatColor.RED+"Żle podane dane!");
					return false;
				}
			case "delete":
				if (arg.length == 2) {
					DeleteWarp warp = new DeleteWarp(arg[1],player);
					return warp.redy();
				}
				else
				{
					player.sendMessage(ChatColor.RED+"Żle podane dane!");
					return false;
				}
			default:
				return false;
			}
		}
			else
			{
				player.sendMessage(ChatColor.RED+"Nie masz uprawnień!");
				return false;
			}
	}
		else
		{ 
			PrintWarp warp = new PrintWarp(player);
			return warp.redy();
		}
		
	}

}
