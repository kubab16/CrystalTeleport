package crystal.teleport;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GetCrystal implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String alias, String[] arg) {
		if(!sender.isOp()) {
			return false;
		}
		Player player = (Player) sender;
		CrystalCreate create = new CrystalCreate();
		ItemStack itemstack = create.crystal(arg[0],arg[1],10);
		itemstack.toString();
		player.getInventory().addItem(itemstack);
		return true;
	}

}
