package cat.math.clearlaglotto.commands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import cat.math.clearlaglotto.ClearLagLotto;
import net.md_5.bungee.api.ChatColor;

public class Pot implements TabExecutor {

	ClearLagLotto plugin;
	
	public Pot(ClearLagLotto plugin) {
		this.plugin = plugin;
	}
	
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		// TODO Auto-generated method stub
		
		String pot = String.format("%.2f", plugin.getLottery().getPot());
		
		if(!(sender instanceof Player) || sender.hasPermission("clearlaglotto.pot")) {
			sender.sendMessage(ChatColor.DARK_AQUA + "There is " + ChatColor.YELLOW + "$" + 
					pot + ChatColor.DARK_AQUA + " in the pot.");
		}
		else {
			sender.sendMessage(ChatColor.RED + "You do not have permission to view how much money is in the pot.");
		}
		
		return true;
	}

}
