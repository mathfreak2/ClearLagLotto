package cat.math.clearlaglotto.commands;

import cat.math.clearlaglotto.ClearLagLotto;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import java.util.ArrayList;
import java.util.List;

public class Cll implements TabExecutor {

	ClearLagLotto plugin;
	
	public Cll(ClearLagLotto plugin) {
		this.plugin = plugin;
	}
	
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {


		if(args.length == 1){

                List<String> list = new ArrayList<>();
                list.add("Reload");
				list.add("");
				list.add("");
				list.add("");
				list.add("");
				list.add("");
				list.add("");
				list.add("");
				list.add("");
				list.add("");
				list.add("");
				list.add("");



                return list;

            }
		return null;
	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		
		if(args.length == 0) return false;
		
		if(args[0].equalsIgnoreCase("reload")) {
			plugin.loadConfig();
			sender.sendMessage("Config reloaded");
			return true;
		}
		
		return false;
	}

}
