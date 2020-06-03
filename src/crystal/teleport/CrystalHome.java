package crystal.teleport;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import server.database.Sethome;

public class CrystalHome implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String alias, String[] arg) {
		Player player = (Player)sender;
		Sethome home = new Sethome(player);
		return home.redy();
	}

}
